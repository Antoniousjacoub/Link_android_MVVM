package com.example.linkdevmvvm.ui.base;

import android.arch.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    protected BaseViewModel() {
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

}