package app.ij.birdwatch;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        clickers();
        startActivity(new Intent(MainActivity.this, ResultActivity.class));
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
                //DONE
                // Need to do the classification here
                // Pass the float array and the bitmap
//                try {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//                    intent.putExtra("image", img);
                    intent.putExtra("image", createImageFromBitmap(img));
//                    intent.putExtra("probs", prediction());
                    startActivity(intent);
//                } catch (Exception e) {
//                    makeToast("Error making prediction " + e.toString());
//                    Log.wtf("*Error making prediction when going to next activity.", e.toString());
//                    e.printStackTrace();
//                }
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
                    makeToast("Ready to identify the bird?");
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
        h.postDelayed(r, 1200);
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