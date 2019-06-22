package com.test.mvvm.ui.feed.blogs;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class BlogEmptyItemViewModel {

    private BlogEmptyItemViewModelListener mListener;

    public BlogEmptyItemViewModel(BlogEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface BlogEmptyItemViewModelListener {

        void onRetryClick();
    }
}
