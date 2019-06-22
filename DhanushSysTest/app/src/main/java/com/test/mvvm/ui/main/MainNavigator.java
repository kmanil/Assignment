package com.test.mvvm.ui.main;

import com.test.mvvm.data.model.api.BlogResponse;

import java.util.List;

/**
 * Created by Kumar anil  on 09/07/17.
 */

public interface MainNavigator {

    void handleError(Throwable throwable);

    void openLoginActivity();

    void updateBlog(List<BlogResponse.Blog> blogList);
}
