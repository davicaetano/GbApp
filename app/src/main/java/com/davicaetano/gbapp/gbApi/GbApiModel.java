package com.davicaetano.gbapp.gbApi;

import com.davicaetano.gbapp.gbModel.GbEvent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by davi on 8/2/17.
 */

//This is the model returned by the Api
public class GbApiModel {
    @SerializedName("total") @Expose
    private String total;

    @SerializedName("data") @Expose
    private List<GbEvent> data = null;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<GbEvent> getData() {
        return data;
    }

    public void setData(List<GbEvent> data) {
        this.data = data;
    }
}
