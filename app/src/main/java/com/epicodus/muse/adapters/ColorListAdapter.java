package com.epicodus.muse.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.muse.R;
import com.epicodus.muse.ui.ArtifactListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.graphics.Color.parseColor;

/**
 * Created by momma on 10/13/17.
 */

public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.ColorViewHolder> {
    public static final String TAG = ColorListAdapter.class.getSimpleName();
    private static final int MAX_WIDTH = 300;
    private static final int MAX_HEIGHT = 300;
    private String[] mColors ;
    private Context mContext;

    public ColorListAdapter(Context context, String[] colors) {
        mContext = context;
        mColors = colors;
    }

    @Override
    public ColorListAdapter.ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_grid_item, parent, false);
        ColorViewHolder viewHolder = new ColorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ColorListAdapter.ColorViewHolder holder, int position) {
        holder.bindColor(mColors[position]);
    }

    @Override
    public int getItemCount() { return mColors.length; }

    public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.grid_item_color) TextView mColorSwatchView;
        @Bind(R.id.grid_item_colorname) TextView mColorSwatchNameView;

        private Context mContext;

        public ColorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            String selectedItem = mColors[itemPosition];
            Intent intent = new Intent(mContext, ArtifactListActivity.class);
            String clickedColor = selectedItem.substring(1);
            Log.v(TAG, ".>>>>>...Color selected: "+clickedColor);
            intent.putExtra("option", "color");
            intent.putExtra("value", clickedColor);
            mContext.startActivity(intent);
        }

        public void bindColor(String color) {
            mColorSwatchView.setBackgroundColor(parseColor(color));
            mColorSwatchNameView.setText(color);
        }
    }
}

