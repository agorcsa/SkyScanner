package com.example.skyscanner;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonSkyScannerAPI {
    // the result of the Retrofit call will be a list of Place JSON objects
    @Headers({
            "x-rapidapi-host: skyscanner-skyscanner-flight-search-v1.p.rapidapi.com",
            "x-rapidapi-key: 548aa9ba87mshcca697ffe0ef206p15a507jsn964a2b1dc9c2"
    })
    @GET("UK/GBP/en-GB/?query=Stockholm")
    Call<Place> getPlaces();
}
