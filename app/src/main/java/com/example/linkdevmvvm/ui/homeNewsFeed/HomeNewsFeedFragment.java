package com.example.linkdevmvvm.ui.homeNewsFeed;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.linkdevmvvm.BR;
import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.ViewModelProviderFactory;
import com.example.linkdevmvvm.adapters.NewsFeedAdapter;
import com.example.linkdevmvvm.dataModel.Article;
import com.example.linkdevmvvm.dataModel.NewsFeedResponse;
import com.example.linkdevmvvm.databinding.FragmentHomeNewsFeedBinding;
import com.example.linkdevmvvm.ui.base.BaseFragment;
import com.example.linkdevmvvm.ui.newsFeedDetails.NewsFeedDetailsActivity;
import com.example.linkdevmvvm.utils.Constants;
import com.example.linkdevmvvm.utils.Utils;

import java.util.Objects;


public class HomeNewsFeedFragment extends BaseFragment<FragmentHomeNewsFeedBinding, HomeNewsFeedViewModel> implements NewsFeedAdapter.OnItemNewsClicked {

    public static final String TAG = "HomeFragment";
    private FragmentHomeNewsFeedBinding fragmentHomeNewsFeedBinding;
    private Context context;
    private HomeNewsFeedViewModel homeNewsFeedViewModel;

    public static HomeNewsFeedFragment getInstance() {
        return new HomeNewsFeedFragment();
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected void setObservers() {
        if (homeNewsFeedViewModel == null) return;
        homeNewsFeedViewModel.getHomeNewsResponse().observe(this, this::onUpdateView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_news_feed;
    }

    @Override
    protected HomeNewsFeedViewModel getViewModel() {
        homeNewsFeedViewModel = ViewModelProviders.of(this, new ViewModelProviderFactory(Objects.requireNonNull(getActivity()).getApplication())).get(HomeNewsFeedViewModel.class);
        return homeNewsFeedViewModel;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        homeNewsFeedViewModel = getViewModel();
        fragmentHomeNewsFeedBinding = getViewDataBinding();
        setObservers();

    }

    private void onUpdateView(NewsFeedResponse newsFeedResponse) {
        Log.e("OnObserverChanged", "OnObserverChanged");
        if (newsFeedResponse == null || context == null) {
            Utils.showMessage(context, getString(R.string.no_data_to_show));
            return;
        }
        fragmentHomeNewsFeedBinding.rvNewsFeed.setLayoutManager(new LinearLayoutManager(context));
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(newsFeedResponse.getArticles(), this);
        fragmentHomeNewsFeedBinding.rvNewsFeed.setAdapter(newsFeedAdapter);
    }

    @Override
    public void onItemNewsClicked(Article article) {
        Intent intent = new Intent(context, NewsFeedDetailsActivity.class);
        intent.putExtra(Constants.ARTICLE_KEY, article);
        startActivity(intent);
    }


}

