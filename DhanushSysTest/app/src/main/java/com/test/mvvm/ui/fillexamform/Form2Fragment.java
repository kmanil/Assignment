package com.test.mvvm.ui.fillexamform;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.data.model.db.Form2;
import com.test.mvvm.databinding.NavigationForm2FragmentBinding;
import com.test.mvvm.ui.base.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Form2Fragment extends BaseFragment<NavigationForm2FragmentBinding, Form2ViewModel1> implements Form2Navigator1 {

    public static final String TAG = Form2Fragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private Form2ViewModel1 mAboutViewModel;
    NavigationForm2FragmentBinding navigationSecondFragmentBinding;
    private Integer id;

    public static Form2Fragment newInstance() {
        Bundle args = new Bundle();
        Form2Fragment fragment = new Form2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_form2_fragment;
    }

    @Override
    public Form2ViewModel1 getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, factory).get(Form2ViewModel1.class);
        return mAboutViewModel;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAboutViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationSecondFragmentBinding = getViewDataBinding();
        id= getArguments().getInt("test_boolean");
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void login() {
        String etSubject1 = navigationSecondFragmentBinding.etSubject1.getText().toString();
        String etSubject2 = navigationSecondFragmentBinding.etSubject2.getText().toString();
        String etSubject3 = navigationSecondFragmentBinding.etSubject3.getText().toString();
        String etSubject4 = navigationSecondFragmentBinding.etSubject4.getText().toString();
        String etSubject5 = navigationSecondFragmentBinding.etSubject5.getText().toString();
        if (mAboutViewModel.isEmailAndPasswordValid(etSubject1,etSubject2,etSubject3,etSubject4,etSubject5)) {
            hideKeyboard();
            Form2 form2=new Form2();
            form2.formId1=id;
            form2.subject1=etSubject1;
            form2.subject2=etSubject2;
            form2.subject3=etSubject3;
            form2.subject4=etSubject4;
            form2.subject5=etSubject5;

            mAboutViewModel.registerNewUser(form2);
        } else {
            Toast.makeText(getActivity(), getString(R.string.invalidate_filed), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void registration() {

        getBaseActivity().onFragmentDetached(TAG);
        final Bundle bundle = new Bundle();
        bundle.putBoolean("test_boolean", false);

        final NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);

        navController.navigateUp();
    }


    @Override
    public void openMainActivity() {
        //Uncomment the below code to Set the message and title from the strings.xml file
        AlertDialog.Builder builder = new AlertDialog.Builder(Form2Fragment.this.getContext());
        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title)
                .setCancelable(false)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();

                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.show();

    }

}