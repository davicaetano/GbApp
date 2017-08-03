package com.davicaetano.gbapp.gbApi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by davi on 8/2/17.
 */

public interface GbService {
    @GET("service/v2/upcomingGuides/")
    Call<GbApiModel> callGbApi();
}
