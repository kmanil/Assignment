package com.test.mvvm.ui.feed.blogs;


import com.test.mvvm.data.model.api.BlogResponse;

import java.util.List;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public interface BlogNavigator {

    void handleError(Throwable throwable);

    void updateBlog(List<BlogResponse.Blog> blogList);
}
