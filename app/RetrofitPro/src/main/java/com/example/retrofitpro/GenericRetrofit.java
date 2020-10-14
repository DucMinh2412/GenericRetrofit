package com.example.retrofitpro;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GenericRetrofit {

    public static <T> void callRetrofitArray(String link, String request, List<T> list) {
        Retrofit restRetrofit = RestRetrofit.getInstance(link);
        RetrofitService retrofitService = restRetrofit.create(RetrofitService.class);
        retrofitService.getArray(request).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                String jsonString = response.body().toString();
                Type listType = new TypeToken<List<T>>() {}.getType();
                list.addAll(new Gson().fromJson(jsonString, listType));
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                 Log.e("Error","Error " + t);
            }
        });
    }
}
