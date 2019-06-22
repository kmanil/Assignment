package com.test.mvvm;


import com.test.mvvm.data.DataManager;
import com.test.mvvm.ui.about.AboutViewModel;
import com.test.mvvm.ui.feed.FeedViewModel;
import com.test.mvvm.ui.feed.blogs.BlogViewModel;
import com.test.mvvm.ui.feed.opensource.OpenSourceViewModel;
import com.test.mvvm.ui.fillexamform.Form1ViewModel1;
import com.test.mvvm.ui.fillexamform.Form2ViewModel1;
import com.test.mvvm.ui.loginregistration.LoginViewModel1;
import com.test.mvvm.ui.loginregistration.RegistrationViewModel1;
import com.test.mvvm.ui.main.MainViewModel;
import com.test.mvvm.ui.main.rating.RateUsViewModel;
import com.test.mvvm.ui.splash.SplashViewModel;
import com.test.mvvm.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AboutViewModel.class)) {
            //noinspection unchecked
            return (T) new AboutViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FeedViewModel.class)) {
            //noinspection unchecked
            return (T) new FeedViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BlogViewModel.class)) {
            //noinspection unchecked
            return (T) new BlogViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RateUsViewModel.class)) {
            //noinspection unchecked
            return (T) new RateUsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(OpenSourceViewModel.class)) {
            //noinspection unchecked
            return (T) new OpenSourceViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel1.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel1(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RegistrationViewModel1.class)) {
            //noinspection unchecked
            return (T) new RegistrationViewModel1(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(Form1ViewModel1.class)) {
            //noinspection unchecked
            return (T) new Form1ViewModel1(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(Form2ViewModel1.class)) {
            //noinspection unchecked
            return (T) new Form2ViewModel1(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}