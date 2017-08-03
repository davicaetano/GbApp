package com.davicaetano.gbapp.view;


import com.davicaetano.gbapp.gbApi.GbApi;
import com.davicaetano.gbapp.gbApi.GbApiModel;
import com.davicaetano.gbapp.gbApi.GbCallback;
import com.davicaetano.gbapp.gbData.GbData;
import com.davicaetano.gbapp.gbModel.GbEvent;

import java.util.List;

/**
 * Created by davi on 8/2/17.
 */

public class MainPresenter {
    private MainActivity mainActivity;
    private GbApi gbApi;
    private GbData gbData;

    public MainPresenter(MainActivity mainActivity, GbApi gbApi, GbData gbData) {
        this.mainActivity = mainActivity;
        this.gbApi = gbApi;
        this.gbData = gbData;
    }

    public void apiOnClick() {
        gbApi.callGbApi(new GbCallback<GbApiModel>() {
            @Override
            public void onSuccess(GbApiModel answer) {
                gbData.setGbEventList(answer.getData());
                mainActivity.refreshAdapter(gbData.getGbEventList());
            }

            @Override
            public void onFailure(Throwable t) {
                mainActivity.dataError();
            }
        }, true);
    }

    public List<GbEvent> getGbEventList() {
        return gbData.getGbEventList();
    }
}
