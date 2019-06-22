package com.test.mvvm.ui.about;


import com.test.mvvm.data.model.db.Form1;

import androidx.databinding.ObservableField;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class AboutItemViewModel {

    public final ObservableField<String> name;

    public final ObservableField<String> address;

    public final ObservableField<String> mobile;

    public final BlogItemViewModelListener mListener;

    public final ObservableField<String> email;
    public final ObservableField<Integer> status ;

    public final ObservableField<String> statusName ;
    public final ObservableField<Boolean> isLoading ;

    private final Form1 mBlog;

    public AboutItemViewModel(Form1 form1, BlogItemViewModelListener listener) {
        this.mBlog =  form1;
        this.mListener = listener;
        name = new ObservableField<>(mBlog.getName());
        mobile = new ObservableField<>(mBlog.getMobile());
        email = new ObservableField<>(mBlog.getEmail());
        address = new ObservableField<>(mBlog.getAddress());
        status = new ObservableField<>(mBlog.getStatus());
        statusName =  new ObservableField<>(mBlog.getStatus()==0 ? "Syncing..":"Pending");
        isLoading =  new ObservableField<>(mBlog.isLoading);

    }


    public void onItemClick() {
        mListener.onItemClick(mBlog.getName());
    }

    public interface BlogItemViewModelListener {

        void onItemClick(String blogUrl);
    }
}
