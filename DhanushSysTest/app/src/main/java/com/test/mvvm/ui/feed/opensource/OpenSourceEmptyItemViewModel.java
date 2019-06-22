package com.test.mvvm.ui.feed.opensource;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class OpenSourceEmptyItemViewModel {

    private final OpenSourceEmptyItemViewModelListener mListener;

    public OpenSourceEmptyItemViewModel(OpenSourceEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface OpenSourceEmptyItemViewModelListener {

        void onRetryClick();
    }
}
