package com.test.mvvm.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.mvvm.data.model.api.BlogResponse;
import com.test.mvvm.databinding.ItemMainEmptyViewBinding;
import com.test.mvvm.databinding.ItemMainViewBinding;
import com.test.mvvm.ui.base.BaseViewHolder;
import com.test.mvvm.ui.feed.FeedActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Kumar Anil on 19/06/2019.
 */

public class MainListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<BlogResponse.Blog> mBlogResponseList;

    private BlogAdapterListener mListener;

    public MainListAdapter(List<BlogResponse.Blog> blogResponseList) {
        this.mBlogResponseList = blogResponseList;
    }

    @Override
    public int getItemCount() {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return mBlogResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBlogResponseList != null && !mBlogResponseList.isEmpty()) {
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
                ItemMainViewBinding blogViewBinding = ItemMainViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new BlogViewHolder(blogViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemMainEmptyViewBinding emptyViewBinding = ItemMainEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<BlogResponse.Blog> blogList) {
        mBlogResponseList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mBlogResponseList.clear();
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    public interface BlogAdapterListener {

        void onRetryClick();
    }

    public class BlogViewHolder extends BaseViewHolder implements  MainItemViewModel.MainItemViewModelListener {

        private ItemMainViewBinding mBinding;

        private MainItemViewModel mBlogItemViewModel;

        public BlogViewHolder(ItemMainViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final BlogResponse.Blog blog = mBlogResponseList.get(position);
            mBlogItemViewModel = new MainItemViewModel(blog, this);
            mBinding.setViewModel(mBlogItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(String blogUrl) {
            Intent intent = FeedActivity.newIntent(itemView.getContext());
            itemView.getContext().startActivity(intent);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements MainEmptyItemViewModel.MainEmptyItemViewModelListener {

        private ItemMainEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemMainEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            MainEmptyItemViewModel emptyItemViewModel = new MainEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}