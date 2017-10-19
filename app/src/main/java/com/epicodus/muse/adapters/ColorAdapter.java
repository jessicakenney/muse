package com.epicodus.muse.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.muse.R;

import static android.graphics.Color.parseColor;

/**
 * Created by momma on 10/13/17.
 */

public class ColorAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mColors;


    @Override
    public int getCount() {
        return mColors.length;
    }

    @Override
    public Object getItem(int position) {
        return mColors[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            // get layout from xml file
            gridView = inflater.inflate(R.layout.color_grid_item, null);


            // pull views
            TextView colorButton = (TextView) gridView
                    .findViewById(R.id.grid_item_color);

            // set values into views
            colorButton.setText(mColors[position]);
            colorButton.setBackgroundColor(parseColor(mColors[position]));
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    public ColorAdapter (Context context, String[] colors){
        this.mContext = context;
        this.mColors = colors;
    }


 }