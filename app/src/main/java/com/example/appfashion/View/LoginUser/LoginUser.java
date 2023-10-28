package com.example.appfashion.View.LoginUser;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Presenter.LoginUser.PresenterLogin;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Home.HomeActivity;
import com.example.appfashion.View.RegisterUser.RegisterUser;

import java.util.Objects;

public class LoginUser extends AppCompatActivity implements ViewLogin{
    EditText emaillogin,passlogin;
    Button btn_dangnhap;
    TextView tv_dangky,tv_forgotpass;
    boolean passwordVisible;
    PresenterLogin presenterLogin;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        Anhxa();
        ActionBar();
        EventHidePassword();
        Login();
        Register();
        setStatusBarColor();
        EventForgotPass();

    }

    private void EventForgotPass() {
        tv_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailforgot;
                AppCompatButton btn_fotgotpass;
                ImageView imgX;
                Dialog dialog = new Dialog(LoginUser.this);
                dialog.setContentView(R.layout.dialog_forgotpass);
                dialog.setCancelable(true);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (dialog != null && dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                emailforgot = dialog.findViewById(R.id.emailforgot);
                btn_fotgotpass = dialog.findViewById(R.id.btn_fotgotpass);
                imgX = dialog.findViewById(R.id.img_x);

                btn_fotgotpass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = emailforgot.getText().toString().trim();
                        if(email.isEmpty()){
                            Toast.makeText(LoginUser.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            presenterLogin.ForgotPass(email);
                        }
                    }
                });
                imgX.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }


    private void ActionBar() {
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


    private void Register() {
        tv_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginUser.this, RegisterUser.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void Login() {
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emaillogin.getText().toString().trim();
                String pass = passlogin.getText().toString().trim();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(LoginUser.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    presenterLogin.loginuser(email,pass);
                }
            }
        });
    }


    @SuppressLint("ClickableViewAccessibility")
    private void EventHidePassword() {
        passlogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=passlogin.getRight()-passlogin.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = passlogin.getSelectionEnd();
                        if(passwordVisible){
                            passlogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_off_24,0);
                            passlogin.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());
                            passwordVisible=false;
                        }else{
                            passlogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_24,0);
                            passlogin.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());
                            passwordVisible=true;
                        }
                        passlogin.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar_login);
        emaillogin = findViewById(R.id.emaillogin);
        passlogin = findViewById(R.id.passlogin);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        tv_dangky = findViewById(R.id.tv_dangky);
        tv_forgotpass = findViewById(R.id.tv_forgotpass);
        presenterLogin = new PresenterLogin(this);
    }



    @Override
    public void LoginSuccess(String email, String pass, ModelUser modelUser) {
        Toast.makeText(LoginUser.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Utils.saveUser(LoginUser.this,modelUser.getId());
        Intent i = new Intent(LoginUser.this, HomeActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    public void LoginFail(ModelUser modelUser) {
        Toast.makeText(LoginUser.this, modelUser.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void LoiTruyCap(Throwable t) {
        Toast.makeText(LoginUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }



    @Override
    public void ForgotPassSuccess() {
        Toast.makeText(getApplicationContext(), "Đã gửi mật khẩu mới đến email. Vui lòng kiểm tra email", Toast.LENGTH_LONG).show();
    }

    @Override
    public void ForgotPassFail(ModelUser user) {
        Toast.makeText(LoginUser.this, user.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void LoitruycapForgotPass(Throwable t) {
        Toast.makeText(LoginUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }
}