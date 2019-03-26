package com.example.linkdevmvvm.ui.homeNewsFeed;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.example.linkdevmvvm.dataModel.NewsFeedResponse;
import com.example.linkdevmvvm.repositories.RepositoryNews;
import com.example.linkdevmvvm.ui.base.BaseViewModel;
import com.example.linkdevmvvm.utils.ResourceState;

;

/**
 * Created by antonio on 1/16/19.
 */
public class HomeNewsFeedViewModel extends BaseViewModel {

    private RepositoryNews repositoryNews;

    public HomeNewsFeedViewModel(Application application) {
        repositoryNews = new RepositoryNews(application);
    }


    MutableLiveData<ResourceState<NewsFeedResponse>> getHomeNewsResponse() {
        return repositoryNews.getArticleList();
    }

    void fetchHomeNewsFeed() {
        repositoryNews.getNewsFeed(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onRefresh() {
        repositoryNews.getNewsFeed(true);
    }


}

