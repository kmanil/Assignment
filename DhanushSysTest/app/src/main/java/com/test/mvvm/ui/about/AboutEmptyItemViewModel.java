package com.test.mvvm.ui.about;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class AboutEmptyItemViewModel {

    private AboutEmptyItemViewModelListener mListener;

    public AboutEmptyItemViewModel(AboutEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface AboutEmptyItemViewModelListener {

        void onRetryClick();
    }
}
