package com.epicodus.muse.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.muse.R;
import com.epicodus.muse.adapters.ColorListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ColorListActivity extends AppCompatActivity {
    public static final String TAG = ColorListActivity.class.getSimpleName();
    //GridView gridView;
    ColorListAdapter mColorAdapter;
    @Bind(R.id.colorsRecyclerView) RecyclerView mRecyclerView;
    String[] colors = new String[]{"#f0f8ff", "#faebd7", "#00ffff", "#7fffd4", "#f0ffff", "#f5f5dc", "#ffe4c4", "#000000", "#ffebcd", "#0000ff", "#8a2be2", "#a52a2a" };

    //Map<String, String> colors = new HashMap<String,String>();
    //String[] colors = new String[]{"aliceblue", "antiquewhite", "cyan", "aquamarine", "azure", "beige", "bisque", "black", "blanchedalmond", "blue", "blueviolet", "brown" };

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
        ButterKnife.bind(this);

        mColorAdapter = new ColorListAdapter(getApplicationContext(), colors);
        mRecyclerView.setAdapter(mColorAdapter);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ColorListActivity.this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //gridView = (GridView) findViewById(R.id.colorGridView);

        //colorAdapter = new ColorListAdapter(this, colors);
        //gridView.setAdapter(colorAdapter);

        //the click listening moveed to the Adapter with Recycler view?
        //gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
         //   @Override
          //  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//       //         String selectedItem = colorAdapter.getItem(position).toString();
//        //        Log.v(TAG, "Color selected: "+selectedItem);
//                Intent intent = new Intent(ColorListActivity.this, ArtifactListActivity.class);
//                intent.putExtra("color", "teal");
//                startActivity(intent);
           // }
    //    });
    }

}
