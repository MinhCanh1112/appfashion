package com.example.appfashion.View.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appfashion.View.ChangePass.ChangePassActivity;
import com.example.appfashion.View.Contact.ContactActivity;
import com.example.appfashion.View.Giohang.GiohangActivity;
import com.example.appfashion.Model.User.ModelUser;
import com.example.appfashion.Presenter.Profile.PresenterProfile;
import com.example.appfashion.R;
import com.example.appfashion.Util.Utils;
import com.example.appfashion.View.Hoadon.HoadonActivity;
import com.example.appfashion.View.LoginUser.LoginUser;
import com.example.appfashion.View.RegisterUser.RegisterUser;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements ViewProfile{
    TextView tv_cartp,tv_orderp,tv_email,tv_doimk,tv_lienhe;
    Button btn_login,btn_register,out;
    LinearLayout ln1,ln2;
    Toolbar toolbar;
    int iduser;
    PresenterProfile presenterProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Anhxa();
        ActionBar();
        Login();
        Logout();
        Register();

        EventIntent(tv_lienhe,ContactActivity.class,"Vui lòng đăng nhập");
        EventIntent(tv_doimk,ChangePassActivity.class,"Vui lòng đăng nhập");
        EventIntent(tv_cartp,GiohangActivity.class,"Vui lòng đăng nhập");
        EventIntent(tv_orderp, HoadonActivity.class,"Vui lòng đăng nhập");


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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24);
    }


    private void EventIntent(TextView tv, Class<?> cls, String text){
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iduser <= 0){
                    Toast.makeText(ProfileActivity.this, text, Toast.LENGTH_SHORT).show();
                }else {
                    Intent i = new Intent(ProfileActivity.this, cls);
                    startActivity(i);
                }
            }
        });
    }

    private void Register() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dk = new Intent(ProfileActivity.this, RegisterUser.class);
                startActivity(dk);
            }
        });
    }

    private void Login() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dn = new Intent(ProfileActivity.this, LoginUser.class);
                startActivity(dn);
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        EventHide();

    }


    private void Logout() {
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.deleteSharePrefer(ProfileActivity.this);
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void EventHide(){
        int iduser = Utils.getUserId(ProfileActivity.this);
        if(iduser <= 0){
            out.setVisibility(View.INVISIBLE);
            ln2.setVisibility(View.INVISIBLE);
            ln1.setVisibility(View.VISIBLE);
        }else {
            presenterProfile.DisplayEmail(iduser);
            ln1.setVisibility(View.INVISIBLE);
            out.setVisibility(View.VISIBLE);
            ln2.setVisibility(View.VISIBLE);
        }
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar_profile);
        out = findViewById(R.id.out);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        tv_cartp = findViewById(R.id.tv_cartp);
        tv_orderp = findViewById(R.id.tv_orderp);
        tv_email = findViewById(R.id.tv_email);
        tv_doimk = findViewById(R.id.tv_doimk);
        tv_lienhe = findViewById(R.id.tv_lienhe);
        ln1 = findViewById(R.id.ln1);
        ln2 = findViewById(R.id.ln2);
        iduser = Utils.getUserId(ProfileActivity.this);
        presenterProfile = new PresenterProfile(this);
    }

    @Override
    public void ShowEmailSuccess(ModelUser user) {
        tv_email.setText(user.getEmail());
    }

    @Override
    public void Loitruycap(Throwable t) {
        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        Log.e("ViewProfileShowEmail" , t.getMessage());
    }
}