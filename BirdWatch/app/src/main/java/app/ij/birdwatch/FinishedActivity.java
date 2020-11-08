package app.ij.birdwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class FinishedActivity extends AppCompatActivity {

    TextView found, message;
    Button info;
    ImageView imageView;
    Bitmap img;
    String birdName = "Albatross";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        info = findViewById(R.id.info);
        found = findViewById(R.id.found);
        message = findViewById(R.id.action);
        imageView = findViewById(R.id.img);

        try {
            img = BitmapFactory.decodeStream(getApplicationContext().openFileInput("myImage"));
            imageView.setImageBitmap(img);
        } catch (FileNotFoundException e) {
            makeToast("Error getting image");
        }

        birdName = Selected.selection.replaceAll("_", "-");

        if (birdName.charAt(0) == 'A' || birdName.charAt(0) == 'E' ||
                birdName.charAt(0) == 'I' || birdName.charAt(0) == 'O' || birdName.charAt(0) == 'U')
            found.setText(found.getText() + "n " + birdName);
        else
            found.setText(found.getText() + " " + birdName);

        if (birdName.equals("None of the above")) {
            info.setVisibility(View.INVISIBLE);
            found.setVisibility(View.INVISIBLE);
        } else {
            clicker();
            level();
        }
    }

    void level() {
        String key = birdName.replaceAll("-", "_");
        String danger = BirdClasses.threat.get(key);
        makeToast(key + " status: " + danger);
        String text = found.getText().toString();
        text += "\nEndangered Level: " + danger;
        SpannableString ss = new SpannableString(text);
        int color = Color.parseColor("#ff3021");
        if (danger.equals("Not Extinct") || danger.equals("Least Concern"))
            color = Color.parseColor("#1c801e");
        else if (danger.equals("Near Threatened"))
            color = Color.parseColor("#db7521");
        else if (danger.equals("Endangered"))
            color = Color.parseColor("#cf4646");
        ss.setSpan(new ForegroundColorSpan(color), text.length() - danger.length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        found.setText(ss);

        if (danger.contains("Endangered"))
            message.setText("We'll use your finding to improve our app and notify authorities of an endangered species sighting.");
    }

    void clicker() {
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/search?q=" + birdName + "+information"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(FinishedActivity.this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finished, menu);
        return true;
    }


    public void makeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

}