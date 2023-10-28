package com.example.appfashion.View.ChangePass;

import com.example.appfashion.Model.User.ModelUser;

public interface ViewChangePass {
    void ChangePassSuccess();
    void ChangePassFail(ModelUser user);
    void Loitruycap(Throwable t);

}
