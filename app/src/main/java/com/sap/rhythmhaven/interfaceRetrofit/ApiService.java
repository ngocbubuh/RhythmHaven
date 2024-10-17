package com.sap.rhythmhaven.interfaceRetrofit;
import com.sap.rhythmhaven.entity.UserEntity;

import java.nio.file.attribute.UserPrincipal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login")
    Call<UserEntity> login(@Body UserEntity user);

    @POST("/auth/register")
    Call<UserEntity> signup(UserEntity newUser);
}