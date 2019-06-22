package com.test.mvvm.ui.fillexamform;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.databinding.NavigationForm1FragmentBinding;
import com.test.mvvm.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Form1Fragment extends BaseFragment<NavigationForm1FragmentBinding, Form1ViewModel1> implements Form1Navigator1 {

    public static final String TAG = Form1Fragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private Form1ViewModel1 loginViewModel1;
    NavigationForm1FragmentBinding navigationFirstFragmentBinding;

    public static Form1Fragment newInstance() {
        Bundle args = new Bundle();
        Form1Fragment fragment = new Form1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_form1_fragment;
    }

    @Override
    public Form1ViewModel1 getViewModel() {
        loginViewModel1 = ViewModelProviders.of(this, factory).get(Form1ViewModel1.class);
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
        navigationFirstFragmentBinding = getViewDataBinding();
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), getString(R.string.invalidate_filed), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void login() {

        String email = navigationFirstFragmentBinding.etEmail.getText().toString();
        String name = navigationFirstFragmentBinding.etName.getText().toString();
        String mobile = navigationFirstFragmentBinding.etMobile.getText().toString();
        String address = navigationFirstFragmentBinding.etAddress.getText().toString();

        if (loginViewModel1.isEmailAndPasswordValid(name, mobile, email, address)) {
            hideKeyboard();
            Form1 form1 = new Form1();
            form1.setName(name);
            form1.setEmail(email);
            form1.setMobile( mobile);
            form1.setAddress(address);
            loginViewModel1.registrationPart1(form1);
        } else {
            Toast.makeText(getActivity(), getString(R.string.invalidate_filed), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void registration() {

    }

    @Override
    public void openMainActivity(Long id) {


        final Bundle bundle = new Bundle();
        bundle.putInt("test_boolean", Integer.parseInt(id.toString()));

        final NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);

        navController.navigate(R.id.action_form1Fragment_to_form2Fragment, bundle);

    }
}
