package com.example.appfashion.View.LoginUser;

import com.example.appfashion.Model.User.ModelUser;

public interface ViewLogin {
    void LoginSuccess(String email, String pass, ModelUser modelUser);
    void LoginFail(ModelUser modelUser);
    void LoiTruyCap(Throwable t);

    void ForgotPassSuccess();
    void ForgotPassFail(ModelUser user);
    void LoitruycapForgotPass(Throwable t);
}
