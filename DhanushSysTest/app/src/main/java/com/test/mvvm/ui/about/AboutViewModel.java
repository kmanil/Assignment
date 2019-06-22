package com.test.mvvm.ui.about;


import com.test.mvvm.data.DataManager;
import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.rx.SchedulerProvider;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AboutViewModel extends BaseViewModel<AboutNavigator> {
    private final MutableLiveData<List<Form1>> formListLiveData;

    public AboutViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        formListLiveData=new MutableLiveData<>();
        fetchBlogs();
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    public void fetchBlogs() {

        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getForm1()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(blogResponse -> {
                    if (blogResponse != null ) {
                        formListLiveData.setValue(blogResponse);
                        getNavigator().updateBlog(blogResponse);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<Form1>> getFormListLiveData() {
        return formListLiveData;
    }
}
