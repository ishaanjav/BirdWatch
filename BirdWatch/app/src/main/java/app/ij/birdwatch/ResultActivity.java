package app.ij.birdwatch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;


import android.app.Dialog;
import android.content.DialogInterface;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {

    float[] prob;
    int top = 10;
    ArrayList<Bird> topResults;
    Bitmap img;
    ImageView image;
    Button next;
    ListView list;
    ArrayList<Pair> pairs;

    //madhu's code
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;

    public double[] location()
    {
        //add permission
        ActivityCompat.requestPermissions(this, new String[]
            {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //Check gps is enable or not
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //enables gps
            OnGPS();
        }
        else
        {
            //GPS is already on
            double[] arr = getLocation();
            return arr;
        }
        return null;
    }

    private double[] getLocation() {
        //Check permissions again
        if (ActivityCompat.checkSelfPermission(ResultActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ResultActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location LocationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            double arr[] = new double[2];
            if (LocationGps != null) {
                double lat = LocationGps.getLatitude();
                double longi = LocationGps.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Log.wtf("Location" , latitude + " " + longitude);
                arr[0] = lat; arr[1] = longi; return arr;
                //showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            } else if (LocationNetwork != null) {
                double lat = LocationNetwork.getLatitude();
                double longi = LocationNetwork.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Log.wtf("Location" , latitude + " " + longitude);
                arr[0] = lat; arr[1] = longi; return arr;
                //showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            } else if (LocationPassive != null) {
                double lat = LocationPassive.getLatitude();
                double longi = LocationPassive.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                Log.wtf("Location" , latitude + " " + longitude);
                arr[0] = lat; arr[1] = longi; return arr;
                //showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            } else {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        return null;
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        return now.toString();
    }

    public MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }


    //end of madhu's code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            loadAndBind();
            //location();
        } catch (FileNotFoundException e) {
            makeToast("Error getting image from previous page.");
        }
        test();
        listener();
//        getResults();
//        loadList();
    }

    void listener() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Selected.chosen) {
                    //README
                    // Show thank you page or something
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