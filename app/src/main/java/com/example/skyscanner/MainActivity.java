package com.example.skyscanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.skyscanner.databinding.ActivityMainBinding;

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

        Call<Root> call = jsonSkyScannerAPI.getPlaces();

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()) {
                    //mainBinding.textViewResult.setText("Code: " + response.code());
                    return;
                }

                Root root = response.body();
                List<Place> places = root.getPlaceList();

                // for each place in our places list, execute the code
                for (Place  place: places){

                    String content = "";
                    content += "PlaceId: " + place.getPlaceId() + "\n";
                    content += "PlaceName: " + place.getPlaceName() + "\n";
                    content += "CountryId: " + place.getCountryId() + "\n";
                    content += "RegionId: " + place.getRegionalId() + "\n";
                    content += "CityId: " + place.getCityId() + "\n";
                    content += "CountryName: " + place.getCountryName() + "\n\n";

                    //mainBinding.textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
               // mainBinding.textViewResult.setText(t.getMessage());
            }
        });
    }
}
