package com.example.skyscanner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    @SerializedName("Places")
    private List<Place> placeList;

    public List<Place> getPlaceList() {
        return placeList;
    }
}
