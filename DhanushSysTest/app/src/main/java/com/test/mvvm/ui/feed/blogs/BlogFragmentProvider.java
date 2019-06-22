package com.test.mvvm.ui.feed.blogs;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public abstract class BlogFragmentProvider {

    @ContributesAndroidInjector(modules = BlogFragmentModule.class)
    abstract BlogFragment provideBlogFragmentFactory();
}
