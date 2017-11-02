package com.epicodus.muse.util;

/**
 * Created by Guest on 11/2/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
