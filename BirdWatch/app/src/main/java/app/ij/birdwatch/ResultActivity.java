package app.ij.birdwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResultActivity extends AppCompatActivity {

    float[] prob;
    int top = 10;
    ArrayList<String> topResults;
    Bitmap img;
    ImageView image;
    Button next;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        loadAndBind();
//        getResults();
        test();
    }

    void test() {
        list = findViewById(R.id.list);
        ArrayList<Row> rowList = new ArrayList<>();
        rowList.add(new Row("A", 0.32f, "B", 0.13f));
        rowList.add(new Row("c", 0.12f, "d", 0.08f));
        rowList.add(new Row("E", 0.10f));
        BirdAdapter adapter = new BirdAdapter(getApplicationContext(), R.layout.bird_row, rowList);
        list.setAdapter(adapter);
    }

    private void loadAndBind() {
        prob = getIntent().getFloatArrayExtra("probs");
        img = getIntent().getParcelableExtra("image");
        image = findViewById(R.id.image);
        next = findViewById(R.id.next);
        image.setImageBitmap(img);
        list = findViewById(R.id.list);
    }

    public void getResults() {
        ArrayList<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < prob.length; i++) {
            pairs.add(new Pair(prob[i], i));
        }
        Collections.sort(pairs, new Sort());
        for (int i = pairs.size() - 1; i > pairs.size() - top; i--)
            topResults.add(BirdClasses.classes.get(pairs.get(i).index));
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