package com.example.appfashion.Presenter.Profile;

import androidx.annotation.NonNull;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Profile.ViewProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterProfile implements iPresenterProfile{
    ViewProfile viewProfile;

    public PresenterProfile(ViewProfile viewProfile) {
        this.viewProfile = viewProfile;
    }

    @Override
    public void DisplayEmail(int iduser) {
        Call<ModelUser> call = Utils.apiUser.getInforUser(iduser);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(@NonNull Call<ModelUser> call, @NonNull Response<ModelUser> response) {
                ModelUser user = response.body();
                viewProfile.ShowEmailSuccess(user);

            }
            @Override
            public void onFailure(@NonNull Call<ModelUser> call, @NonNull Throwable t) {
                viewProfile.Loitruycap(t);

            }
        });
    }
}
