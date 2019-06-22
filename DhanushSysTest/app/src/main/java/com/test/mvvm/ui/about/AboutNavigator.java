package com.test.mvvm.ui.about;

import com.test.mvvm.data.model.db.Form1;

import java.util.List;

/**
 * Created by Kumar anil  on 09/07/17.
 */

public interface AboutNavigator {

    void goBack();
    void handleError(Throwable throwable);

    void openLoginActivity();

    void updateBlog(List<Form1> form1List);
}
