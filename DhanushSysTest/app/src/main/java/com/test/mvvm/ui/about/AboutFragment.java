package com.test.mvvm.ui.about;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import com.test.mvvm.BR;
import com.test.mvvm.R;
import com.test.mvvm.ViewModelProviderFactory;
import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.databinding.FragmentAboutBinding;
import com.test.mvvm.scheduler.SyncDataJobScheduler;
import com.test.mvvm.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AboutFragment extends BaseFragment<FragmentAboutBinding, AboutViewModel> implements AboutNavigator, AboutAdapter.BlogAdapterListener {

    public static final String TAG = AboutFragment.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private AboutViewModel mAboutViewModel;
    @Inject
    AboutAdapter mBlogAdapter;
    FragmentAboutBinding fragmentAboutBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    private MyReceiver receiver;
    private List<Form1> form1List=new ArrayList<>();


    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutViewModel getViewModel() {
        mAboutViewModel = ViewModelProviders.of(this,factory).get(AboutViewModel.class);
        return mAboutViewModel;

    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void updateBlog(List<Form1> form1List) {
        this.form1List=form1List;
        mBlogAdapter.addItems(form1List);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentAboutBinding = getViewDataBinding();
        mAboutViewModel.setNavigator(this);
        mBlogAdapter.setListener(this);
        setUp();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ComponentName componentName = new ComponentName(getBaseActivity(), SyncDataJobScheduler.class);
            final JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();

            JobScheduler jobScheduler = (JobScheduler) getBaseActivity().getSystemService(
                    Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(jobInfo);
        }
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("download.file.com");
        getBaseActivity().registerReceiver(receiver, intentFilter);

    }

    private void setUp() {
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentAboutBinding.mainRecyclerView.setLayoutManager(mLayoutManager);
        fragmentAboutBinding.mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentAboutBinding.mainRecyclerView.setAdapter(mBlogAdapter);
    }

    @Override
    public void onRetryClick() {
        mAboutViewModel.fetchBlogs();

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                String message = intent.getStringExtra("message");
                for(int i=0;i<form1List.size();i++){
                    form1List.get(i).isLoading=false;
                    form1List.get(i).setAddress(message);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBlogAdapter.clearItems();
                        mBlogAdapter.addItems(form1List);
                    }
                }, 1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiver!=null){
            try {
                getBaseActivity().unregisterReceiver(receiver);
            }catch (Exception e){}
        }
    }
}
