package com.test.mvvm.ui.loginregistration;

/**
 * Created by Kumar anil  on 08/07/17.
 */

public interface LoginNavigator1 {

    void handleError(Throwable throwable);

    void login();

    void registration();

    void openMainActivity();
}
