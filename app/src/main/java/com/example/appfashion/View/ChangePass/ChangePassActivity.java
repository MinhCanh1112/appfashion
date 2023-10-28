package com.example.appfashion.View.ChangePass;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Presenter.ChangePass.PresenterChangePass;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;

import java.util.Objects;

public class ChangePassActivity extends AppCompatActivity implements ViewChangePass{
    EditText pass_old,pass_new,confirmpass_new;
    AppCompatButton btn_changepass;
    Toolbar toolbar;
    int iduser;
    PresenterChangePass presenterChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Anhxa();
        ActionToolBar();
        EventChangePass();
        setStatusBarColor();

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void EventChangePass() {
        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass= pass_old.getText().toString().trim();
                String newpass = pass_new.getText().toString().trim();
                String passcf = confirmpass_new.getText().toString().toString();
                if(pass.isEmpty()||newpass.isEmpty()||passcf.isEmpty()) {
                    Toast.makeText(ChangePassActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if(!passcf.equals(newpass)){
                    Toast.makeText(ChangePassActivity.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                }else{
                    presenterChangePass.ChangePass(iduser,pass,newpass);
                }
            }
        });
    }


    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar_changepass);
        pass_old = findViewById(R.id.pass_old);
        pass_new = findViewById(R.id.pass_new);
        confirmpass_new = findViewById(R.id.confirmpass_new);
        btn_changepass = findViewById(R.id.btn_changepass);
        iduser = Utils.getUserId(ChangePassActivity.this);
        presenterChangePass = new PresenterChangePass(this);
    }

    @Override
    public void ChangePassSuccess() {
        Toast.makeText(ChangePassActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ChangePassFail(ModelUser user) {
        Toast.makeText(ChangePassActivity.this, user.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Loitruycap(Throwable t) {
        Toast.makeText(ChangePassActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}