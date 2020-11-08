package app.ij.birdwatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class BirdAdapter extends ArrayAdapter<Row> {

    private int resourceLayout;
    private Context context;
    List<Row> list;

    public BirdAdapter(Context context, int resource, List<Row> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
        list = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.bird_row, parent, false);
        Row bird = list.get(position);

        RelativeLayout bg = v.findViewById(R.id.bg);
        if (position % 2 == 1)
            bg.setBackgroundColor(Color.WHITE);
        else
            bg.setBackgroundColor(Color.parseColor("#ECFFC0"));

        ImageView image = v.findViewById(R.id.image);
        TextView name = v.findViewById(R.id.name);
        TextView prob = v.findViewById(R.id.prob);
        CardView box1, box2;
        RelativeLayout rl = v.findViewById(R.id.w1);
        RelativeLayout rl2 = v.findViewById(R.id.w2);
        box2 = v.findViewById(R.id.card2);

        box1 = v.findViewById(R.id.card1);
        // README
        // Display results - image has to be based off of name
        name.setText(bird.bird1.replaceAll("_", "-"));
        prob.setText(" " + bird.prob1 + " %");


        // No second bird
        if (!bird.present) {
            box2.setVisibility(View.INVISIBLE);
        } else {
            box2.setVisibility(View.VISIBLE);
            setSecond(position, v);
        }


        // Selection
        RelativeLayout wrapper = v.findViewById(R.id.wrapper1);
        RelativeLayout stuff = v.findViewById(R.id.stuff);
        if (bird.bird1.equals("special")) {
            stuff.setVisibility(View.INVISIBLE);
            box1.setOnClickListener(listener2("None of the above", wrapper));
            wrapper.setVisibility(View.VISIBLE);
        } else {
            box1.setOnClickListener(selectListener(bird.bird1, rl, image));
            stuff.setVisibility(View.VISIBLE);
            wrapper.setVisibility(View.INVISIBLE);
        }
        wrapper = v.findViewById(R.id.wrapper2);
        stuff = v.findViewById(R.id.stuff2);
        if (bird.present && bird.bird2.equals("special")) {
            box2.setOnClickListener(listener2("None of the above", wrapper));
            stuff.setVisibility(View.INVISIBLE);
            wrapper.setVisibility(View.VISIBLE);
        } else {
            box2.setOnClickListener(selectListener(bird.bird2, rl2, (ImageView) v.findViewById(R.id.image2)));
            stuff.setVisibility(View.VISIBLE);
            wrapper.setVisibility(View.INVISIBLE);
        }

        if (!bird.bird1.equals("special"))
            getBirdImage(position, image, 1);
        return v;
    }

    public void getBirdImage(int pos, ImageView image, int id) {
        Resources res = context.getResources();
        String drawableName = formatName(list.get(pos).bird1);
        if (id == 2) drawableName = formatName(list.get(pos).bird2);
        int resId = res.getIdentifier(drawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resId);
        image.setImageDrawable(drawable);
        Log.wtf("Drawable: ", drawableName);
    }

    public View.OnClickListener listener2(final String s, final RelativeLayout rel) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int backgroundColor = ((CardView) (view)).getCardBackgroundColor().getDefaultColor();
                if (Selected.chosen) {
                    if (Selected.selection.equals(s)) {
                        Selected.chosen = false;
                        rel.setBackgroundColor(Color.parseColor("#ffffff"));
                    } else
                        makeToast("You have already selected: " + Selected.selection);
                } else {
                    Selected.selection = s;
                    Selected.chosen = true;
                    rel.setBackgroundColor(Color.parseColor("#b5b5b5"));
                }
                Log.wtf("*Color:", " " + backgroundColor);
            }
        };
    }

    public View.OnClickListener selectListener(final String s, final RelativeLayout rel, final ImageView img) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int backgroundColor = ((CardView) (view)).getCardBackgroundColor().getDefaultColor();
                if (Selected.chosen) {
                    if (Selected.selection.equals(s)) {
                        Selected.chosen = false;
                        rel.setBackgroundColor(Color.parseColor("#ffffff"));
                        grayOut(img);
                    } else
                        makeToast("You have already selected: " + Selected.selection);
                } else {
                    Selected.selection = s;
                    grayOut(img);
                    Selected.chosen = true;
                    rel.setBackgroundColor(Color.parseColor("#b5b5b5"));
                }
                Log.wtf("*Color:", " " + backgroundColor);
            }
        };
    }

    public void grayOut(ImageView view) {
        if (view.getTag() != "grayed") {
            view.setColorFilter(Color.argb(150, 200, 200, 200));
            view.setTag("grayed");
        } else {
            view.setColorFilter(null);
            view.setTag("");
        }
    }

    public void setSecond(int pos, View v) {
        Row bird = list.get(pos);
        ImageView image = v.findViewById(R.id.image2);
        TextView name = v.findViewById(R.id.name2);
        TextView prob = v.findViewById(R.id.prob2);
        name.setText(bird.bird2.replaceAll("_", "-"));
        prob.setText(" " + bird.prob2 + " %");

        getBirdImage(pos, image, 2);
    }

    public String formatName(String s) {
        s = s.toLowerCase();
        s = s.replaceAll(" ", "_");
        return s;
    }

    public void makeToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}