package com.example.appfashion.View.RegisterUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Presenter.RegisterUser.PresenterRegister;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Home.HomeActivity;
import com.example.appfashion.View.LoginUser.LoginUser;

import java.util.Objects;

public class RegisterUser extends AppCompatActivity implements ViewRegister{
    EditText edt_pass,edt_cfpass,edt_email;
    TextView tv_dangnhap;
    AppCompatButton btn_dangky;
    boolean passwordVisible;
    PresenterRegister presenterRegister;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Anhxa();
        ActionBar();
        EventHidePassword();
        Login();
        Register();
        setStatusBarColor();


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
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString().trim();
                String pass = edt_pass.getText().toString().trim();
                String cfpass = edt_cfpass.getText().toString().trim();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText(RegisterUser.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(cfpass)){
                    Toast.makeText(RegisterUser.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                } else{
                    presenterRegister.RegisterUser(email,pass);
                }

            }
        });
    }

    private void Login() {
        tv_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dn = new Intent(RegisterUser.this, LoginUser.class);
                startActivity(dn);
                finish();

            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar_register);
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        edt_cfpass = findViewById(R.id.edt_cfpass);
        tv_dangnhap = findViewById(R.id.tv1_dangnhap);
        btn_dangky = findViewById(R.id.btn1_dangki);
        presenterRegister = new PresenterRegister(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void EventHidePassword() {
        edt_pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edt_pass.getRight()-edt_pass.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = edt_pass.getSelectionEnd();
                        if(passwordVisible){
                            edt_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_off_24,0);
                            edt_pass.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());
                            passwordVisible=false;
                        }else{
                            edt_pass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_24,0);
                            edt_pass.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());
                            passwordVisible=true;
                        }
                        edt_pass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
        edt_cfpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=edt_cfpass.getRight()-edt_cfpass.getCompoundDrawables()[Right].getBounds().width()){

                        int selection = edt_cfpass.getSelectionEnd();
                        if(passwordVisible){
                            edt_cfpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_off_24,0);
                            edt_cfpass.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());
                            passwordVisible=false;
                        }else{
                            edt_cfpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_key_24,0,R.drawable.ic_baseline_visibility_24,0);
                            edt_cfpass.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());
                            passwordVisible=true;
                        }
                        edt_cfpass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
    }



    @Override
    public void RegisterSuccess(String email, String pass, ModelUser modelUser) {
        Utils.saveUser(RegisterUser.this, modelUser.getId());
        Toast.makeText(RegisterUser.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RegisterUser.this, HomeActivity.class);
        startActivity(i);
        finishAffinity();
    }

    @Override
    public void RegisterFail(ModelUser modelUser) {
        Toast.makeText(RegisterUser.this, modelUser.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void LoiTruyCap(Throwable t) {
        Toast.makeText(RegisterUser.this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}