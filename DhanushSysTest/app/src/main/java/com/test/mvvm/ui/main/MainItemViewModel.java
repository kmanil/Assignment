package com.test.mvvm.ui.main;


import com.test.mvvm.data.model.api.BlogResponse;

import androidx.databinding.ObservableField;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class MainItemViewModel {

    public final ObservableField<String> author;

    public final ObservableField<String> content;

    public final ObservableField<String> date;

    public final ObservableField<String> imageUrl;

    public final MainItemViewModelListener mListener;

    public final ObservableField<String> title;

    private final BlogResponse.Blog mBlog;

    public MainItemViewModel(BlogResponse.Blog blog, MainItemViewModelListener listener) {
        this.mBlog = blog;
        this.mListener = listener;
        imageUrl = new ObservableField<>(mBlog.getCoverImgUrl());
        title = new ObservableField<>(mBlog.getTitle());
        author = new ObservableField<>(mBlog.getAuthor());
        date = new ObservableField<>(mBlog.getDate());
        content = new ObservableField<>(mBlog.getDescription());
    }

    public void onItemClick() {
        mListener.onItemClick(mBlog.getBlogUrl());
    }

    public interface MainItemViewModelListener {

        void onItemClick(String blogUrl);
    }
}
