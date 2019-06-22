package com.test.mvvm.ui.about;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Kumar anil  on 14/09/17.
 */
@Module
public class AboutFragmentModule {

    @Provides
    AboutAdapter provideBlogAdapter() {
        return new AboutAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AboutFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}
