package com.example.appfashion.Presenter.LoginUser;



import androidx.annotation.NonNull;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.LoginUser.ViewLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogin implements iPresenterLogin{
    ViewLogin viewLogin;

    public PresenterLogin(ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }


    @Override
    public void loginuser(String email, String pass) {

        Call<ModelUser> call = Utils.apiUser.login(email,pass);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(@NonNull Call<ModelUser> call, @NonNull Response<ModelUser> response) {
                ModelUser modelUser = response.body();
                if(modelUser!=null&&modelUser.isSuccess()){
                    viewLogin.LoginSuccess(email,pass,modelUser);
                }else{
                    viewLogin.LoginFail(modelUser);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ModelUser> call, @NonNull Throwable t) {
                viewLogin.LoiTruyCap(t);
            }
        });
    }

    @Override
    public void ForgotPass(String email) {
        Call<ModelUser> call = Utils.apiUser.forgotpass(email);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(@NonNull Call<ModelUser> call, @NonNull Response<ModelUser> response) {
                ModelUser modelUser = response.body();
                if(modelUser!=null&&modelUser.isSuccess()){
                    viewLogin.ForgotPassSuccess();
                }else{
                    viewLogin.ForgotPassFail(modelUser);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ModelUser> call, @NonNull Throwable t) {
                viewLogin.LoitruycapForgotPass(t);
            }
        });
    }
}
