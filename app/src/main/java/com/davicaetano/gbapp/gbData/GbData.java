package com.davicaetano.gbapp.gbData;


import com.davicaetano.gbapp.gbModel.GbEvent;

import java.util.List;

/**
 * Created by davi on 8/2/17.
 */

//This does the persistence. The data is stored only on memory because the scope of the project.
public class GbData {
    private List<GbEvent> gbEventList;

    public List<GbEvent> getGbEventList() {
        return gbEventList;
    }

    public void setGbEventList(List<GbEvent> gbEventList) {
        this.gbEventList = gbEventList;
    }
}
