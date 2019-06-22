package com.test.mvvm.ui.feed;


import com.test.mvvm.data.DataManager;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.rx.SchedulerProvider;

public class FeedViewModel extends BaseViewModel {

    public FeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
