package com.sap.rhythmhaven.interfaceRetrofit;
import com.sap.rhythmhaven.entity.UserEntity;
import com.sap.rhythmhaven.entity.UserEntitySignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login")
    Call<UserEntity> login(@Body UserEntity user);

    @POST("/auth/register")
    Call<UserEntitySignUp> signup(@Body UserEntitySignUp newUser);
}
