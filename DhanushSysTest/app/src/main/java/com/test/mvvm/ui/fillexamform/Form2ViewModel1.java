package com.test.mvvm.ui.fillexamform;

import android.text.TextUtils;

import com.test.mvvm.data.DataManager;
import com.test.mvvm.data.model.db.Form2;
import com.test.mvvm.ui.base.BaseViewModel;
import com.test.mvvm.utils.rx.SchedulerProvider;


public class Form2ViewModel1 extends BaseViewModel<Form2Navigator1> {

    public Form2ViewModel1(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isEmailAndPasswordValid(String name, String mobile, String email, String password, String etSubject5) {
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
        if (TextUtils.isEmpty(etSubject5)) {
            return false;
        }
        return true;
    }

    public void registerNewUser(Form2 user) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .insertForm2(user)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onRegistrationClick() {
    getNavigator().login();
    }




}
