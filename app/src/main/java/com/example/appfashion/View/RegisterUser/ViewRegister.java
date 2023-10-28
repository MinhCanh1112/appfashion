package com.example.appfashion.View.RegisterUser;

import com.example.appfashion.Model.User.ModelUser;

public interface ViewRegister {
    void RegisterSuccess(String email,String pass, ModelUser modelUser);
    void RegisterFail(ModelUser modelUser);
    void LoiTruyCap(Throwable t);
}
