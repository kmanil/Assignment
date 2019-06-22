package com.test.mvvm.di.builder;

import com.test.mvvm.ui.about.AboutFragmentModule;
import com.test.mvvm.ui.about.AboutFragmentProvider;
import com.test.mvvm.ui.feed.FeedActivity;
import com.test.mvvm.ui.feed.FeedActivityModule;
import com.test.mvvm.ui.feed.blogs.BlogFragmentProvider;
import com.test.mvvm.ui.feed.opensource.OpenSourceFragmentProvider;
import com.test.mvvm.ui.fillexamform.FillExamFormActivity;
import com.test.mvvm.ui.fillexamform.Form1FragmentProvider;
import com.test.mvvm.ui.fillexamform.Form2FragmentProvider;
import com.test.mvvm.ui.loginregistration.LoginFragmentProvider;
import com.test.mvvm.ui.loginregistration.LoginRegistrationActivity;
import com.test.mvvm.ui.loginregistration.RegistrationFragmentProvider;
import com.test.mvvm.ui.main.MainActivity;
import com.test.mvvm.ui.main.MainFragmentModule;
import com.test.mvvm.ui.main.rating.RateUsDialogProvider;
import com.test.mvvm.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class})
    abstract FeedActivity bindFeedActivity();

    @ContributesAndroidInjector(modules = {
            LoginFragmentProvider.class,
            RegistrationFragmentProvider.class})
    abstract LoginRegistrationActivity bindLoginRegistrationActivity();

    @ContributesAndroidInjector(modules = {
            Form1FragmentProvider.class,
            Form2FragmentProvider.class})
    abstract FillExamFormActivity bindFillExamFormActivity();

    @ContributesAndroidInjector(modules = {
            AboutFragmentProvider.class,
            AboutFragmentModule.class,
            RateUsDialogProvider.class,
            MainFragmentModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
