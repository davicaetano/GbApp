package com.davicaetano.gbapp;

import android.app.Application;

import com.davicaetano.gbapp.gbApi.GbApi;
import com.davicaetano.gbapp.gbApi.GbRetrofit;
import com.davicaetano.gbapp.view.MainActivity;
import com.davicaetano.gbapp.view.MainPresenter;

/**
 * Created by davi on 8/2/17.
 */

public class GbApp extends Application {
    private GbApi gbApi;
    private GbRetrofit gbRetrofit;

    //The code below does DI. I didn't implement Dagger because the scope of the App is small.
    public MainPresenter getMainPresenter(MainActivity mainActivity) {
        return new MainPresenter(mainActivity, getGbApi());
    }

    private GbApi getGbApi() {
        if (gbApi == null) {
            gbApi = new GbApi(getGbRetrofit());
        }
        return gbApi;
    }

    private GbRetrofit getGbRetrofit() {
        if (gbRetrofit == null) {
            gbRetrofit = new GbRetrofit();
        }
        return gbRetrofit;
    }
}
