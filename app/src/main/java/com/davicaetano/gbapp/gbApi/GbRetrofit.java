package com.davicaetano.gbapp.gbApi;

import android.util.Log;

import com.davicaetano.gbapp.gbModel.GbEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davi on 8/2/17.
 */

//This is the implementation of Retrofit to access the data.
public class GbRetrofit {
    private static final String GB_URL = "https://guidebook.com";
    public final String TAG = this.getClass().getSimpleName();

    private Retrofit retrofit;
    private GbService gbService;

    public GbRetrofit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(getGbUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gbService = retrofit.create(GbService.class);
    }

    public String getGbUrl() {
        return GB_URL;
    }

    public void call(final GbCallback<GbApiModel> callback, boolean async) {
        Call<GbApiModel> gbServiceCall = gbService.callGbApi();

        if (async) {
            gbServiceCall.enqueue(new Callback<GbApiModel>() {
                @Override
                public void onResponse(Call<GbApiModel> call, Response<GbApiModel> response) {
                    Log.v(TAG, "onResponse");
                    if (response.body() != null && response.body().getTotal() != null) {
                        Log.v(TAG, "Total: " + response.body().getTotal());
                    }
                    if (response.body() != null && response.body().getData() != null) {
                        for (GbEvent event : response.body().getData()) {
                            if (event != null) {
                                Log.v(TAG, event.toString());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<GbApiModel> call, Throwable t) {
                    Log.v(TAG, "onFailure");
                    Log.v(TAG, t.getMessage());
                    callback.onFailure(t);
                }
            });
        } else {//Sync is used by tests
            try {
                callback.onSuccess(gbServiceCall.execute().body());
            } catch (Exception e) {
                callback.onFailure(new Throwable());
            }

        }
    }


}
