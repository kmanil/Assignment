package com.test.mvvm.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.mvvm.data.model.db.Form1;
import com.test.mvvm.databinding.ItemAboutEmptyViewBinding;
import com.test.mvvm.databinding.ItemAboutViewBinding;
import com.test.mvvm.ui.base.BaseViewHolder;
import com.test.mvvm.utils.AppLogger;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class AboutAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Form1> form1List;

    private BlogAdapterListener mListener;

    public AboutAdapter(List<Form1> blogResponseList) {
        this.form1List = blogResponseList;
    }

    @Override
    public int getItemCount() {
        if (form1List != null && form1List.size() > 0) {
            return form1List.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (form1List != null && !form1List.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemAboutViewBinding blogViewBinding = ItemAboutViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new BlogViewHolder(blogViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemAboutEmptyViewBinding emptyViewBinding = ItemAboutEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<Form1> blogList) {
        form1List.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        form1List.clear();
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    public interface BlogAdapterListener {

        void onRetryClick();
    }

    public class BlogViewHolder extends BaseViewHolder implements AboutItemViewModel.BlogItemViewModelListener {

        private ItemAboutViewBinding mBinding;

        private AboutItemViewModel mBlogItemViewModel;

        public BlogViewHolder(ItemAboutViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Form1 blog = form1List.get(position);
            mBlogItemViewModel = new AboutItemViewModel(blog, this);
            mBinding.setViewModel(mBlogItemViewModel);
            if (blog.isLoading)
                mBinding.addressLookingUp.setVisibility(View.VISIBLE);
            else
                mBinding.addressLookingUp.setVisibility(View.GONE);


            mBinding.statusTextView.setText(blog.getAddress());


            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String blogUrl) {
            if (blogUrl != null) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    AppLogger.d("url error");
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements AboutEmptyItemViewModel.AboutEmptyItemViewModelListener {

        private ItemAboutEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemAboutEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            AboutEmptyItemViewModel emptyItemViewModel = new AboutEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}