package com.test.mvvm.ui.about;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public abstract class AboutFragmentProvider {

    @ContributesAndroidInjector(modules = AboutFragmentModule.class)
    abstract AboutFragment provideAboutFragmentFactory();
}
