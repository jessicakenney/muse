package com.epicodus.muse.adapters;

/**
 * Created by Guest on 11/2/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.muse.Constants;
import com.epicodus.muse.models.Artifact;
import com.epicodus.muse.ui.ArtifactDetailActivity;
import com.epicodus.muse.util.ItemTouchHelperAdapter;
import com.epicodus.muse.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Guest on 11/2/17.
 */

public class FirebaseArtifactListAdapter extends FirebaseRecyclerAdapter<Artifact, FirebaseArtifactViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Artifact> mArtifacts = new ArrayList<>();
    private int mOrientation;

    public FirebaseArtifactListAdapter(Class<Artifact> modelClass, int modelLayout,
                                       Class<FirebaseArtifactViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener  =  mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mArtifacts.add(dataSnapshot.getValue(Artifact.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void setIndexInFirebase() {
        for (Artifact artifact : mArtifacts) {
            int index = mArtifacts.indexOf(artifact);
            //getRef(index) ? firebase knows the index?
            DatabaseReference ref = getRef(index);
//            artifact.setIndex(Integer.toString(index));
            ref.setValue(artifact);
        }
    }

    @Override
    protected void populateViewHolder(final FirebaseArtifactViewHolder viewHolder, Artifact model, int position) {
        viewHolder.bindArtifact(model);
        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            createDetailFragment(0);
        }
        viewHolder.mArtifactImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = viewHolder.getAdapterPosition();
                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    createDetailFragment(itemPosition);
                } else {
                    Intent intent = new Intent(mContext, ArtifactDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                    intent.putExtra(Constants.EXTRA_KEY_ARTIFACTS, Parcels.wrap(mArtifacts));
                    mContext.startActivity(intent);
                }
            }
        });

    }

    private void createDetailFragment(int position) {

//        // Creates new ArtifactDetailFragment with the given position:
//        ArtifactDetailFragment detailFragment = ArtifactDetailFragment.newInstance(mArtifacts, position);
//
//        // Gathers necessary components to replace the FrameLayout in the layout with the ArtifactDetailFragment:
//        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//
//        //  Replaces the FrameLayout with the ArtifactDetailFragment:
//        ft.replace(R.id.artifactDetailContainer, detailFragment);
//
//        // Commits these changes:
//        ft.commit();
    }



    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mArtifacts, fromPosition, toPosition);
        notifyItemMoved(fromPosition,toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mArtifacts.remove(position);
        getRef(position).removeValue();

    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }


}
