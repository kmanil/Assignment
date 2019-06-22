package com.test.mvvm.ui.loginregistration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.databinding.ActivityLoginRegistrationBinding;
import com.test.mvvm.databinding.ActivityMainBinding;
import com.test.mvvm.databinding.NavigationFirstFragmentBinding;
import com.test.mvvm.ui.base.BaseActivity;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class LoginRegistrationActivity extends BaseActivity<ActivityLoginRegistrationBinding, LoginViewModel1> implements LoginNavigator1, HasSupportFragmentInjector {


    public static final String TAG = LoginRegistrationActivity.class.getSimpleName();
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel1 mAboutViewModel;
    private ActivityLoginRegistrationBinding mActivityMainBinding;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginRegistrationActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_registration;
    }

    @Override
    public LoginViewModel1 getViewModel() {
          mAboutViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel1.class);
        return mAboutViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mAboutViewModel.setNavigator(this);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void login() {

    }

    @Override
    public void registration() {

    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
