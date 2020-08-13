package com.example.skyscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/autosuggest/v1.0/UK/GBP/en-GB/?query=Stockholm")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonSkyScannerAPI jsonSkyScannerAPI = retrofit.create(JsonSkyScannerAPI.class);

        Call<List<Place>> call = jsonSkyScannerAPI.getPlaces();

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Place> places = response.body();

                // for each place in our places list, execute the code
                for (Place place: places) {

                    String content = "";
                    content += "PlaceId: " + place.getPlaceId() + "\n";
                    content += "PlaceName: " + place.getPlaceName() + "\n";
                    content += "CountryId: " + place.getCountryId() + "\n";
                    content += "RegionId: " + place.getRegionalId() + "\n";
                    content += "CityId: " + place.getCityId() + "\n";
                    content += "CountryName: " + place.getCountryName() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
