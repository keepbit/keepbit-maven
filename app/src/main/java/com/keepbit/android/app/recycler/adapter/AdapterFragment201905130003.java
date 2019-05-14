package com.keepbit.android.app.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.keepbit.android.app.recycler.viewholder.ViewHolderFragment201905130003;

/**
 * Created by CoderMario on 2019-05-13.
 */
public class AdapterFragment201905130003 extends RecyclerView.Adapter<ViewHolderFragment201905130003> {

    @NonNull @Override
    public ViewHolderFragment201905130003 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderFragment201905130003(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFragment201905130003 viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
