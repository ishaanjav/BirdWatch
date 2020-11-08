package app.ij.birdwatch;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.Interpreter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab, identify;
    int PICTURE_RESULT = 1, GALLERY_RESULT = 2;
    int REQUEST_CAMERA = 1034, REQUEST_GALLERY = 1000, REQUEST_GALLERY_FROM_CAMERA = 234;

    Uri imageUri;
    Bitmap img;
    ImageView image;
    ContentValues values;
    CardView card;
    TextView prompt;
    Interpreter tflite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BirdClasses.init();

        bindViews();
        clickers();
//        startActivity(new Intent(MainActivity.this, FinishedActivity.class));
        try {
            tflite = new Interpreter(loadModelFile());
        } catch (IOException e) {
            makeToast("Error getting model.");
            Log.wtf("*Model Loading Error", e.toString());
        }
        test();
    }


    void test() {
        try {
            tflite = new Interpreter(loadModelFile());
            img = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.drawable.capuchinbird);
            image.setImageBitmap(img);
//            classifyImage(img);
//            classifyImage(BitmapFactory.decodeResource(getApplicationContext().getResources(),
//                    R.drawable.albatross));
        } catch (Exception e) {
            makeToast("Error getting model.");
            Log.wtf("*Model Loading Error", e.toString());
        }

    }

    private float[][] classifyImage(Bitmap bitmap) {
        int imageSizeX = 224;
        int imageSizeY = 224;

        // initialize output array
        float[][] inputVal = new float[1][225];
        bitmap = getResizedBitmap(bitmap, imageSizeX, imageSizeY);

        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * 1 *
                224 * 224 * 3); //float_size = 4 bytes
        byteBuffer.order(ByteOrder.nativeOrder());
        int[] intValues = new int[224 * 224];
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0,
                bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;

        for (int i = 0; i < 224; ++i) {
            for (int j = 0; j < 224; ++j) {
                final int val = intValues[pixel++];
                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
            }
        }
//        Log.wtf("Byte", byteBuffer + "");

        tflite.run(byteBuffer, inputVal);
//        Log.wtf("Results", Arrays.toString(inputVal[0]));
        return inputVal;
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }

    public MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("bird_classifier.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public String createImageFromBitmap(Bitmap bitmap) {
        String fileName = "myImage";//no .png or .jpg needed
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            fileName = null;
        }
        return fileName;
    }

    private void clickers() {
        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                float[][] prediction = classifyImage(img);
                intent.putExtra("image", createImageFromBitmap(img));
                Log.wtf("Length: ", prediction[0].length + "");
                intent.putExtra("probs", prediction[0]);
//                classifyImage(img);
//                image.setImageBitmap(img);
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                } else {
                    launchCamera();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // They didn't cancel
        if (resultCode == RESULT_OK) {
            if (requestCode == PICTURE_RESULT) {
                try {
                    img = MediaStore.Images.Media.getBitmap(
                            getContentResolver(), imageUri);
                    img = ImageHandling.centerCrop(img);
                    img = ImageHandling.handleSamplingAndRotationBitmap(getApplicationContext(), imageUri);
                    image.setImageBitmap(img);
                    makeToast("Click on the search button on the right!");
                    prompt.setText("Identify the Bird!");

                    showIdentifier();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.wtf("*Error getting image:", e.toString());
                    makeToast("Error getting image");
                }
            } else {
                //Later on for gallery
            }
        }
    }

    public void showIdentifier() {
        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
                fadeIn.setDuration(1000);
                identify.setVisibility(View.VISIBLE);
                identify.setAnimation(fadeIn);
            }
        };
        h.postDelayed(r, 1225);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            }
        }
    }

    void launchCamera() {
        try {
            values = new ContentValues();
            imageUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, PICTURE_RESULT);
        } catch (Exception e) {
//            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent, 3);
            Log.wtf("*Error launching Camera", e.toString());
            makeToast("Unable to launch camera.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void bindViews() {
        image = findViewById(R.id.image);
        fab = findViewById(R.id.fab);
        card = findViewById(R.id.card);
        prompt = findViewById(R.id.prompt);
        identify = findViewById(R.id.identify);
        card.setBackgroundResource(R.drawable.green_bg);
    }

    public static String assetFilePath(Context context, String assetName) throws IOException {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        }
    }

    public void makeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}