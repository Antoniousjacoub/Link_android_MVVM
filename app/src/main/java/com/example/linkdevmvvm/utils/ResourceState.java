package com.example.linkdevmvvm.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class ResourceState<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private ResourceState(@NonNull Status status, @Nullable T data,
                          @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> ResourceState<T> success(@NonNull T data) {
        return new ResourceState<>(Status.SUCCESS, data, null);
    }

    public static <T> ResourceState<T> error(String msg, @Nullable T data) {
        return new ResourceState<>(Status.ERROR, data, msg);
    }

    public static <T> ResourceState<T> loading(@Nullable T data) {
        return new ResourceState<>(Status.LOADING, data, null);
    }

    public static <T> ResourceState<T> refreshing(@Nullable T data) {
        return new ResourceState<>(Status.REFRESHING, data, null);
    }

    public enum Status {SUCCESS, ERROR, LOADING, REFRESHING}
}