package com.example.appfashion.Presenter.RegisterUser;

import androidx.annotation.NonNull;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.RegisterUser.ViewRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterRegister implements iPresenterRegister{
    ViewRegister viewRegister;

    public PresenterRegister(ViewRegister viewRegister) {
        this.viewRegister = viewRegister;
    }

    @Override
    public void RegisterUser(String email, String pass) {
        Call<ModelUser> call = Utils.apiUser.register(email,pass);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(@NonNull Call<ModelUser> call, @NonNull Response<ModelUser> response) {
                ModelUser modelUser = response.body();
                if(modelUser!=null&&modelUser.isSuccess()){
                    viewRegister.RegisterSuccess(email,pass,modelUser);
                }else{
                    viewRegister.RegisterFail(modelUser);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelUser> call, @NonNull Throwable t) {
                viewRegister.LoiTruyCap(t);

            }
        });
    }
}
