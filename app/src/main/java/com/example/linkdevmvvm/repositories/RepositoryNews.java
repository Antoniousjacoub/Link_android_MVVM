package com.example.linkdevmvvm.repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.linkdevmvvm.App;
import com.example.linkdevmvvm.BuildConfig;
import com.example.linkdevmvvm.R;
import com.example.linkdevmvvm.dataModel.NewsFeedResponse;
import com.example.linkdevmvvm.service.ServicesInterface;
import com.example.linkdevmvvm.utils.Constants;
import com.example.linkdevmvvm.utils.ResourceState;
import com.example.linkdevmvvm.utils.Utils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryNews {

    @Inject
    ServicesInterface servicesInterface;

    private MutableLiveData<ResourceState<NewsFeedResponse>> articleList;
    private Application application;

    public RepositoryNews(Application application) {
        this.application = application;
        App.getNetComponent().inject(this);
        articleList = new MutableLiveData<>();
    }

    public void getNewsFeed(boolean fromSwipeRefresh) {
        Call<NewsFeedResponse> homeData = servicesInterface.getNewsFeed(Constants.SOURCE, BuildConfig.API_KEY);
        if (fromSwipeRefresh) {
            articleList.setValue(ResourceState.refreshing(null));
        } else {
            articleList.setValue(ResourceState.loading(null));
        }
        homeData.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeedResponse> call, @NonNull final Response<NewsFeedResponse> response) {
                if (fromSwipeRefresh) {
                    articleList.setValue(ResourceState.refreshing(null));
                } else {
                    articleList.setValue(ResourceState.loading(null));
                }
                if (response.isSuccessful() && response.body() != null) {
                    articleList.setValue(ResourceState.success(response.body()));
                } else {
                    articleList.setValue(ResourceState.error(application.getString(R.string.somthing_went_wrong), null));
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsFeedResponse> call, @NonNull Throwable t) {
                articleList.setValue(ResourceState.error(Utils.processError(application, t), null));
            }
        });


    }

    public MutableLiveData<ResourceState<NewsFeedResponse>> getArticleList() {
        return articleList;
    }

}
