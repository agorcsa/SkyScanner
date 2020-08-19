package com.example.skyscanner;

import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("PlaceId")
    private String placeId;
    @SerializedName("PlaceName")
    private String placeName;
    @SerializedName("CountryId")
    private String countryId;
    @SerializedName("RegionId")
    private String regionalId;
    @SerializedName("CityId")
    private String cityId;
    @SerializedName("CountryName")
    private String countryName;

    public Place(String placeId, String placeName, String countryId, String regionalId, String cityId, String countryName) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.countryId = countryId;
        this.regionalId = regionalId;
        this.cityId = cityId;
        this.countryName = countryName;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getCountryId() {
        return countryId;
    }

    public String getRegionalId() {
        return regionalId;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCountryName() {
        return countryName;
    }
}
