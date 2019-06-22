package com.test.mvvm.ui.loginregistration;

import android.text.TextUtils;

import com.test.mvvm.data.DataManager;
import com.test.mvvm.data.model.db.User;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.rx.SchedulerProvider;


public class RegistrationViewModel1 extends BaseViewModel<RegistrationNavigator1> {

    public RegistrationViewModel1(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String name, String mobile, String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(name)) {
            return false;
        }if (TextUtils.isEmpty(mobile)) {
            return false;
        }if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void registerNewUser(User user) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .insertUser(user)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    setIsLoading(false);
                    getDataManager()
                            .updateUserInfo(
                                    "Dhanush@1234556",
                                    1L,
                                    DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                    user.name,
                                    user.email,
                                    "");
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onRegistrationClick() {
    getNavigator().login();
    }



    public void onServerLoginClick() {
        getNavigator().registration();
    }
}
