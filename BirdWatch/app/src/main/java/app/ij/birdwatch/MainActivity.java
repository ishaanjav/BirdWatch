package app.ij.birdwatch;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    int PICTURE_RESULT = 1, GALLERY_RESULT = 2;
    int REQUEST_CAMERA = 1034, REQUEST_GALLERY = 1000, REQUEST_GALLERY_FROM_CAMERA = 234;

    Uri imageUri;
    Bitmap img;
    ImageView image;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        fab = findViewById(R.id.fab);
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

                    image.setImageBitmap(img);
                    makeToast("Got the image");
                } catch (Exception e) {
                    e.printStackTrace();
                    makeToast("Error getting image");
                }
            } else {
                //Later on for gallery
            }
        }
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

    public void makeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
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

    public String modelLearning() throws IOException {
        //replace below line to include the image from gallery.
        Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open("image.jpg"));
        Module module = Module.load(assetFilePath(this, "bird-model.pth"));

        // showing image on UI
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);

        // preparing input tensor
        Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap,
                TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB);

        // running the model
        Tensor outputTensor = module.forward(IValue.from(inputTensor)).toTensor();
        // getting tensor content as java array of floats
        float[] scores = outputTensor.getDataAsFloatArray();

        // searching for the index with maximum score
        float maxScore = -Float.MAX_VALUE;
        int maxScoreIdx = -1;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                maxScoreIdx = i;
            }
        }

        //not sure if im supposed to use the arraylist or the linked list.
        String className = BirdClasses.classes.get(maxScoreIdx);
        return className;
    }

}