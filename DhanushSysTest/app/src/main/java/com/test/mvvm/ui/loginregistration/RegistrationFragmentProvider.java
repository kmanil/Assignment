package com.test.mvvm.ui.loginregistration;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public abstract class RegistrationFragmentProvider {

    @ContributesAndroidInjector
    abstract RegistrationFragment provideAboutFragmentFactory();
}
