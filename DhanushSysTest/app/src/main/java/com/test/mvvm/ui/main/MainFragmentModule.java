package com.test.mvvm.ui.main;


import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public class MainFragmentModule {

    @Provides
    MainListAdapter provideBlogAdapter() {
        return new MainListAdapter(new ArrayList<>());
    }

    @Provides
    GridLayoutManager provideLinearLayoutManager(MainActivity activity) {
        return new GridLayoutManager(activity,3);
    }
}
