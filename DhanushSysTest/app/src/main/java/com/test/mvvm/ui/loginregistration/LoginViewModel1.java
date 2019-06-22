package com.test.mvvm.ui.loginregistration;

import android.text.TextUtils;

import com.test.mvvm.data.DataManager;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.CommonUtils;
import com.test.mvvm.utils.rx.SchedulerProvider;


public class LoginViewModel1 extends BaseViewModel<LoginNavigator1> {

    public LoginViewModel1(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void login(String email, String password) {
          setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getUsersByCredentials(email,password)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(user -> {
                    setIsLoading(false);
                    if (user!=null) {
                        getDataManager()
                                .updateUserInfo(
                                        "demo.token.from.mock.serve",
                                        1L,
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                        user.name,
                                        user.email,
                                        "");
                        getNavigator().openMainActivity();
                    }else {
                        getNavigator().handleError(null);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onRegistrationClick() {

        getNavigator().registration();
     //   setIsLoading(true);
      /*  User user = new User();
        user.id= Long.valueOf(1);
        user.name="ANil";

        getDataManager().insertUser(user);
        Log.d("afaf",getDataManager().getAllUsers().toString());
        Log.d("afaf", DebugDB.getAddressLog());*/

       /* getCompositeDisposable().add(getDataManager()

                .insertUser((user)));*/
               /* .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                response.getAccessToken(),
                                response.getUserId(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
                                response.getUserName(),
                                response.getUserEmail(),
                                response.getGoogleProfilePicUrl()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));*/
    }



    public void onServerLoginClick() {
        getNavigator().login();
    }
}
