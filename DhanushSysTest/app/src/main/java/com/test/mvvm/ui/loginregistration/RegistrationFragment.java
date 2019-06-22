package com.test.mvvm.ui.loginregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.data.model.db.User;
import com.test.mvvm.databinding.NavigationSecondFragmentBinding;
import com.test.mvvm.ui.base.BaseFragment;
import com.test.mvvm.ui.main.MainActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class RegistrationFragment extends BaseFragment<NavigationSecondFragmentBinding, RegistrationViewModel1> implements RegistrationNavigator1 {

    public static final String TAG = RegistrationFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private RegistrationViewModel1 mAboutViewModel;
    NavigationSecondFragmentBinding navigationSecondFragmentBinding;

    public static RegistrationFragment newInstance() {
        Bundle args = new Bundle();
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_second_fragment;
    }

    @Override
    public RegistrationViewModel1 getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this, factory).get(RegistrationViewModel1.class);
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
        navigationSecondFragmentBinding=getViewDataBinding();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void login() {
        String email = navigationSecondFragmentBinding.etEmail.getText().toString();
        String password = navigationSecondFragmentBinding.etPassword.getText().toString();
        String name = navigationSecondFragmentBinding.etName.getText().toString();
        String mobile = navigationSecondFragmentBinding.etMobile.getText().toString();
        if (mAboutViewModel.isEmailAndPasswordValid(name,mobile,email, password)) {
            hideKeyboard();
            User user=new User();
            user.name=name;
            user.mobile=mobile;
            user.email=email;
            user.password=password;

            mAboutViewModel.registerNewUser(user);
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
        Intent intent = MainActivity.newIntent(getActivity());
        startActivity(intent);
        getActivity().finish();

    }

}/* extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.navigation_second_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity().getApplicationContext(), "Bundle args " + getArguments().getBoolean("test_boolean"), Toast.LENGTH_SHORT).show();
       // Toast.makeText(getActivity().getApplicationContext(), "Bundle args " + FirstFragmentArgs.fromBundle(getArguments()).getTestString(), Toast.LENGTH_SHORT).show();

        Button button = view.findViewById(R.id.button_frag2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
                navController.navigateUp();

                navController.addOnNavigatedListener(new NavController.OnNavigatedListener() {
                    @Override
                    public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                        Log.d("TAG", destination.getLabel() + " ");
                    }
                });
            }
        });
    }

}
*/