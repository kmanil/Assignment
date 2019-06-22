package com.test.mvvm.ui.feed;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public class FeedActivityModule {

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(FeedActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

}
