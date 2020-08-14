package com.example.groceryapp.API;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST(WebServer.SIGNUP_KEY)
    Call<User> createUser(
      @Field("name") String name,
      @Field("email") String email,
      @Field("password") String password,
      @Field("password_confirmation") String password_confirmation
    );

    @FormUrlEncoded
    @POST(WebServer.LOGIN_KEY)
    Call<User> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

}
