package com.test.mvvm.ui.fillexamform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.databinding.ActivityFillExamFormBinding;
import com.test.mvvm.ui.base.BaseActivity;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class FillExamFormActivity extends BaseActivity<ActivityFillExamFormBinding, Form1ViewModel1> implements Form1Navigator1, HasSupportFragmentInjector {


    public static final String TAG = FillExamFormActivity.class.getSimpleName();
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    ViewModelProviderFactory factory;
    private Form1ViewModel1 mAboutViewModel;
    private ActivityFillExamFormBinding mActivityMainBinding;


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, FillExamFormActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_exam_form;
    }

    @Override
    public Form1ViewModel1 getViewModel() {
          mAboutViewModel = ViewModelProviders.of(this,factory).get(Form1ViewModel1.class);
        return mAboutViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
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
    public void openMainActivity(Long id) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
