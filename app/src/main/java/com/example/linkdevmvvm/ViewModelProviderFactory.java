package com.example.linkdevmvvm;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.linkdevmvvm.ui.homeNewsFeed.HomeNewsFeedViewModel;
import com.example.linkdevmvvm.ui.newsFeedDetails.NewsDetailsViewModel;


public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {


    private Application application;
    private Bundle bundle;

    public ViewModelProviderFactory(Application application, Bundle bundle) {
        this.application = application;
        this.bundle = bundle;
    }

    public ViewModelProviderFactory(Application application) {
        this.application = application;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new NewsDetailsViewModel(application, bundle);
        } else if (modelClass.isAssignableFrom(HomeNewsFeedViewModel.class)) {
            //noinspection unchecked
            return (T) new HomeNewsFeedViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
