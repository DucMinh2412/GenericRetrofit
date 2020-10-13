package com.example.retrofitpro;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface RetrofitService {

      @GET
      Call<JsonArray> getArray(@Url String request);

      @GET
      Call<JsonObject> getObject(@Url String request);

//    @POST("/posts")
//    @FormUrlEncoded
//    Call<User> getPostData(@Field("title") String title,
//                                @Field("userId") String userId,
//                                @Field("body") String body);
}
