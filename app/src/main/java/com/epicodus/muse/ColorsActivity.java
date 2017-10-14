package com.epicodus.muse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class ColorsActivity extends AppCompatActivity {
    GridView gridView;
    ColorAdapter colorAdapter;
    //Map<String, String> colors = new HashMap<String,String>();

    //String[] colors = new String[]{"aliceblue", "antiquewhite", "cyan", "aquamarine", "azure", "beige", "bisque", "black", "blanchedalmond", "blue", "blueviolet", "brown" };
    String[] colors = new String[]{"#f0f8ff", "#faebd7", "#00ffff", "#7fffd4", "#f0ffff", "#f5f5dc", "#ffe4c4", "#000000", "#ffebcd", "#0000ff", "#8a2be2", "#a52a2a" };

//    colors.put("#f0f8ff", "aliceblue" );
//    colors.put("#faebd7", "antiquewhite" ),
//    colors.put("#00ffff", "cyan" );
//    colors.put("#7fffd4", "aquamarine" );
//    colors.put("#f0ffff", "azure" );
//    colors.put("#f5f5dc", "beige" );
//    colors.put("#ffe4c4", "bisque" );
//    colors.put("#000000", "black" );
//    colors.put("#ffebcd", "blanchedalmond" );
//    colors.put("#0000ff", "blue" );
//    colors.put("#8a2be2", "blueviolet");
//    colors.put("#a52a2a", "brown");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        gridView = (GridView) findViewById(R.id.colorGridView);
        gridView.setAdapter(new ColorAdapter(this, colors));

    }


}
