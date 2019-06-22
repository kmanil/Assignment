package com.test.mvvm.ui.main;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class MainEmptyItemViewModel {

    private MainEmptyItemViewModelListener mListener;

    public MainEmptyItemViewModel(MainEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface MainEmptyItemViewModelListener {

        void onRetryClick();
    }
}
