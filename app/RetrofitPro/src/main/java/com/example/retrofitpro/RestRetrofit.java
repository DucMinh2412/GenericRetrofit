package com.example.retrofitpro;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestRetrofit {
    public static Retrofit retrofit;

    public static Retrofit getInstance(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
