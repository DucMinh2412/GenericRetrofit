package com.example.retrofitpro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class GenericRetrofit<T>{
    String link;
    String request;
    Activity activity;
    List<T> list;
    Type listType = new TypeToken<List<T>>() {}.getType();

    public GenericRetrofit(String link, String request, Activity activity, List<T> list) {
        this.link = link;
        this.request = request;
        this.activity = activity;
        this.list = list;
    }

    public void GetDataJsonArray() {
        Retrofit restRetrofit = RestRetrofit.getInstance(link);
        RetrofitService retrofitService = restRetrofit.create(RetrofitService.class);
        retrofitService.getArray(request).enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()) {
                    String jsonString = response.body().toString();
                    SaveData(jsonString);
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("Error","Error " + t);
            }
        });
    }

    private void SaveData(String JsonString){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("MY_SHARE.f", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("JsonString", JsonString);
        editor.commit();
    }

    public void GetData(){
        SharedPreferences prefs = activity.getSharedPreferences("MY_SHARE.f", Context.MODE_PRIVATE);
        String JsonString = prefs.getString("JsonString",null);
        list.addAll(new Gson().fromJson(JsonString,listType));
    }
}
