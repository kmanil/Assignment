package com.test.mvvm.ui.fillexamform;

import android.text.TextUtils;

import com.test.mvvm.data.DataManager;
import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.CommonUtils;
import com.test.mvvm.utils.rx.SchedulerProvider;


public class Form1ViewModel1 extends BaseViewModel<Form1Navigator1> {

    public Form1ViewModel1(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String name, String mobile, String email, String address) {
        // validate email and password
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        if (TextUtils.isEmpty(mobile)) {
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(address)) {
            return false;
        }
        return true;
    }

    public void registrationPart1(Form1 form1) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .insertForm1(form1)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity(aBoolean);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onRegistrationClick() {

        getNavigator().login();
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



   /* public void onServerLoginClick() {
        getNavigator().login();
    }*/
}
