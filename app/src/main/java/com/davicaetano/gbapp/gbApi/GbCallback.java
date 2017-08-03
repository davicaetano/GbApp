package com.davicaetano.gbapp.gbApi;

/**
 * Created by davi on 8/2/17.
 */
//This is a generic callback independent from framework
public interface GbCallback<T> {
    void onSuccess(T answer);
    void onFailure(Throwable t);
}
