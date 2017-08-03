package com.davicaetano.gbapp.view;


import com.davicaetano.gbapp.gbApi.GbApi;
import com.davicaetano.gbapp.gbApi.GbApiModel;
import com.davicaetano.gbapp.gbApi.GbCallback;

/**
 * Created by davi on 8/2/17.
 */

public class MainPresenter {
    private GbApi gbApi;
    private MainActivity mainActivity;

    public MainPresenter(MainActivity mainActivity, GbApi gbApi) {
        this.gbApi = gbApi;
        this.mainActivity = mainActivity;
    }

    public void apiOnClick() {
        gbApi.callGbApi(new GbCallback<GbApiModel>() {
            @Override
            public void onSuccess(GbApiModel answer) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, true);
    }
}
