package com.example.skyscanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import com.example.skyscanner.databinding.ActivityMainBinding;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);

        fetchDataFromApi();
    }

    public void fetchDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonSkyScannerAPI jsonSkyScannerAPI = retrofit.create(JsonSkyScannerAPI.class);

        Call<Place> call = jsonSkyScannerAPI.getPlaces();

        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                if (!response.isSuccessful()) {
                    mainBinding.textViewResult.setText("Code: " + response.code());
                    return;
                }

                Place place = response.body();

                String content = "";
                content += "PlaceId: " + place.getPlaceId() + "\n";
                content += "PlaceName: " + place.getPlaceName() + "\n";
                content += "CountryId: " + place.getCountryId() + "\n";
                content += "RegionId: " + place.getRegionalId() + "\n";
                content += "CityId: " + place.getCityId() + "\n";
                content += "CountryName: " + place.getCountryName() + "\n\n";

                mainBinding.textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                mainBinding.textViewResult.setText(t.getMessage());
            }
        });
    }
}
