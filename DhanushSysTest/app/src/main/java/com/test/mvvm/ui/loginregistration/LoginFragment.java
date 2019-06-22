package com.test.mvvm.ui.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.databinding.NavigationFirstFragmentBinding;
import com.test.mvvm.ui.base.BaseFragment;
import com.test.mvvm.ui.main.MainActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class LoginFragment extends BaseFragment<NavigationFirstFragmentBinding, LoginViewModel1> implements LoginNavigator1 {

    public static final String TAG = LoginFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel1 loginViewModel1;
    NavigationFirstFragmentBinding navigationFirstFragmentBinding;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_first_fragment;
    }

    @Override
    public LoginViewModel1 getViewModel() {
        loginViewModel1 = ViewModelProviders.of(this,factory).get(LoginViewModel1.class);
        return loginViewModel1;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel1.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationFirstFragmentBinding=getViewDataBinding();
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void login() {

        String email = navigationFirstFragmentBinding.etEmail.getText().toString();
        String password = navigationFirstFragmentBinding.etPassword.getText().toString();
        if (loginViewModel1.isEmailAndPasswordValid(email, password)) {
            hideKeyboard();
            loginViewModel1.login(email, password);
        } else {
            Toast.makeText(getActivity(), getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void registration() {


        final Bundle bundle = new Bundle();
        bundle.putBoolean("test_boolean", false);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);

        navController.navigate(R.id.action_loginFragment_to_registrationFragment, bundle);
    }

    @Override
    public void openMainActivity() {

        Intent intent = MainActivity.newIntent(getActivity());
        startActivity(intent);
        getActivity().finish();

    }
}
