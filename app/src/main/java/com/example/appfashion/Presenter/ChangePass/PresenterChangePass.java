package com.example.appfashion.Presenter.ChangePass;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.ChangePass.ViewChangePass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterChangePass implements iPresenterChangePass{
    ViewChangePass viewChangePass;

    public PresenterChangePass(ViewChangePass viewChangePass) {
        this.viewChangePass = viewChangePass;
    }

    @Override
    public void ChangePass(int iduser, String pass, String newpass) {
        Call<ModelUser> call = Utils.apiUser.changePass(iduser,pass,newpass);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                ModelUser user = response.body();
                if(user!=null&& user.isSuccess()){
                    viewChangePass.ChangePassSuccess();
                }else {
                    viewChangePass.ChangePassFail(user);
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                viewChangePass.Loitruycap(t);
            }
        });
    }
}
