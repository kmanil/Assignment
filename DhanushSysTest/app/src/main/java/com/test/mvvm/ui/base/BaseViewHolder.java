package com.test.mvvm.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kumar anil  on 11/07/17.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
