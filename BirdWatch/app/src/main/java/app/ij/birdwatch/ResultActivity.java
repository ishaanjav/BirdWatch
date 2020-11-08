package app.ij.birdwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResultActivity extends AppCompatActivity {

    float[] prob;
    int top = 10;
    ArrayList<Bird> topResults;
    Bitmap img;
    ImageView image;
    Button next;
    ListView list;
    ArrayList<Pair> pairs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            loadAndBind();
        } catch (FileNotFoundException e) {
            makeToast("Error getting image from previous page.");
        }
        test();
        listener();
//        getResults();
//        loadList();
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
    void listener() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Selected.chosen) {
                    //README
                    // Show thank you page or something
                    Intent intent = new Intent(getApplicationContext(), FinishedActivity.class);
                    intent.putExtra("image", createImageFromBitmap(img));
                    startActivity(intent);
                } else {
                    makeToast("Please select one of the options");
                }
            }
        });

    }

    void loadList() {
        ArrayList<Row> rowList = new ArrayList<>();
        for (int i = 0; i < top; i += 2) {
            // Only 1 bird in this row
            if (i == top - 1) {
                rowList.add(new Row(topResults.get(i).bird, topResults.get(i).prob));
            } else {
                rowList.add(new Row(topResults.get(i).bird, topResults.get(i).prob,
                        topResults.get(i + 1).bird, topResults.get(i + 1).prob));
            }
        }
        // Add "none of the above" option
        Row lastRow = rowList.get(rowList.size() - 1);
        if (lastRow.present) {
            Row newRow = new Row("special", 0);
            rowList.add(newRow);
        } else {
            lastRow = new Row(lastRow.bird1, lastRow.prob1, "special", 0);
            rowList.set(rowList.size() - 1, lastRow);
        }
        BirdAdapter adapter = new BirdAdapter(getApplicationContext(), R.layout.bird_row, rowList);
        list.setAdapter(adapter);
    }


    void test() {
        list = findViewById(R.id.list);
        ArrayList<Row> rowList = new ArrayList<>();
        rowList.add(new Row("Albatross", 0.32f, "Alexandrine Parakeet", 0.13f));
        rowList.add(new Row("American Redstart", 0.12f, "Black_necked Grebe", 0.08f));
//        rowList.add(new Row("E", 0.10f));
        rowList.add(new Row("Black_throated Sparrow", 0.10f, "Cock Of The Rock", 0.01f));
        Row lastRow = rowList.get(rowList.size() - 1);
        if (lastRow.present) {
            Row newRow = new Row("special", 0);
            rowList.add(newRow);
        } else {
            lastRow = new Row(lastRow.bird1, lastRow.prob1, "special", 0);
            rowList.set(rowList.size() - 1, lastRow);
        }
        BirdAdapter adapter = new BirdAdapter(getApplicationContext(), R.layout.bird_row, rowList);
        list.setAdapter(adapter);
    }

    private void loadAndBind() throws FileNotFoundException {
//        prob = getIntent().getFloatArrayExtra("probs");
        img = BitmapFactory.decodeStream(getApplicationContext().openFileInput("myImage"));
        image = findViewById(R.id.image);
        next = findViewById(R.id.next);
//        image.setImageBitmap(img);
        list = findViewById(R.id.list);
    }

    public void getResults() {
        pairs = new ArrayList<>();
        for (int i = 0; i < prob.length; i++) {
            pairs.add(new Pair(prob[i], i));
        }
        Collections.sort(pairs, new Sort());
        for (int i = pairs.size() - 1; i > pairs.size() - top; i--)
            topResults.add(new Bird(BirdClasses.classes.get(pairs.get(i).index), pairs.get(i).prob));
        makeToast("Got: " + topResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class Bird {
        String bird;
        float prob;

        public Bird(String b, float p) {
            bird = b;
            prob = p;
        }
    }

    public static class Pair {
        float prob;
        int index;

        public Pair(float i, int j) {
            i = i;
            index = j;
        }

        public Pair(Pair p) {
            prob = p.prob;
            index = p.index;
        }

        public String toString() {
            return prob + "," + index;
        }

        public Pair clone() {
            return new Pair(prob, index);
        }
    }

    // x then y
    class Sort implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            if (a.prob == b.prob)
                return a.index - b.index;
            if (a.prob == b.prob) return 0;
            if (a.prob > b.prob) return 1;
            return -1;
        }
    }

    public void makeToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}