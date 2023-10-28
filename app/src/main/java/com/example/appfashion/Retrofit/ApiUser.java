package com.example.appfashion.Retrofit;

import com.example.appfashion.Model.User.ModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUser {
    @POST("registerUser.php")
    @FormUrlEncoded
    Call<ModelUser> register(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("loginUser.php")
    @FormUrlEncoded
    Call<ModelUser> login(
            @Field("email") String email,
            @Field("pass") String pass
    );


    @POST("getInforUser.php")
    @FormUrlEncoded
    Call<ModelUser> getInforUser(
            @Field("iduser") int iduser
    );

    @POST("forgotpass.php")
    @FormUrlEncoded
    Call<ModelUser> forgotpass(
            @Field("email") String email
    );

    @POST("changePass.php")
    @FormUrlEncoded
    Call<ModelUser> changePass(
            @Field("id") int iduser,
            @Field("pass") String pass,
            @Field("newpass") String newpass
    );
}
