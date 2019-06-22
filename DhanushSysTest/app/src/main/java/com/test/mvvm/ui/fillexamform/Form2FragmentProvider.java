package com.test.mvvm.ui.fillexamform;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public abstract class Form2FragmentProvider {

    @ContributesAndroidInjector
    abstract Form2Fragment provideAboutFragmentFactory();
}
