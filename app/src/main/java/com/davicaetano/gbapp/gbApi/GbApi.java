package com.davicaetano.gbapp.gbApi;

/**
 * Created by davi on 8/2/17.
 */

//This class is a generic class. It can use any implementation that fetch the data.
public class GbApi {
    public final String TAG = this.getClass().getSimpleName();

    private GbRetrofit gbRetrofit;

    public GbApi(GbRetrofit gbRetrofit) {
        this.gbRetrofit = gbRetrofit;
    }

    public void callGbApi() {
        gbRetrofit.call();
    }

}
