package com.example.retrofitpro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GenericRetrofit<T> {
    String link;
    String request;
    List<T> list;

    public GenericRetrofit(String link, String request, List<T> list) {
        this.link = link;
        this.request = request;
        this.list = list;
    }

    public void GetData() {
        Retrofit restRetrofit = RestRetrofit.getInstance(link);
        RetrofitService retrofitService = restRetrofit.create(RetrofitService.class);
        Call<JsonArray> call = retrofitService.getArray(request);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                String jsonString = response.body().toString();
                Type listType = new TypeToken<List<T>>() {}.getType();
                list.clear();
                list.addAll(new Gson().fromJson(jsonString, listType));
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Error",t + " ");
            }
        });

    }
}
