package com.epicodus.muse.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ColorAdapter;

public class ColorsActivity extends AppCompatActivity {
    public static final String TAG = ColorsActivity.class.getSimpleName();
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

        colorAdapter = new ColorAdapter(this, colors);
        gridView.setAdapter(colorAdapter);

        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = colorAdapter.getItem(position).toString();
                //Toast.makeText(ColorsActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Color selected: "+selectedItem);
                Intent intent = new Intent(ColorsActivity.this, ArtifactsListActivity.class);
                intent.putExtra("color", "lightpink");
                startActivity(intent);
            }
        });
    }


}
