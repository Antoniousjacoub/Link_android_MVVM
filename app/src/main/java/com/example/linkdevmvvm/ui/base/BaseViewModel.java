package com.example.linkdevmvvm.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.example.linkdevmvvm.App;
import com.example.linkdevmvvm.service.ServicesInterface;

import javax.inject.Inject;

import retrofit2.Call;

public abstract class BaseViewModel extends ViewModel {

    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    private final ObservableBoolean mIsRefreshing = new ObservableBoolean();
    @Inject
    ServicesInterface servicesInterface;
    private Call call;


    protected BaseViewModel() {
        App.getNetComponent().inject(this);

    }

    @Override
    protected void onCleared() {
        if (call != null) {
            call.cancel();
        }
        super.onCleared();
    }

    protected ServicesInterface getServicesInterface() {
        return servicesInterface;
    }

    protected void setCurrentRetrofitCall(Call currentRetrofitCall) {
        this.call = currentRetrofitCall;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public ObservableBoolean getIsRefreshing() {
        return mIsRefreshing;
    }

    public void setIsRefreshing(boolean isRefreshing) {
        mIsRefreshing.set(isRefreshing);
    }
}