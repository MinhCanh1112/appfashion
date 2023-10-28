package com.example.appfashion.View.Contact;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.appfashion.R;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout callphone,facebook,zalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Anhxa();
        ActionToolBar();
        setStatusBarColor();
        EventCallPhone();
        EventFaceBook();
        EventZalo();

    }

    private void EventZalo() {
        zalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "zalo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EventFaceBook() {
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "facebook", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EventCallPhone() {
        callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactActivity.this, "callphone", Toast.LENGTH_SHORT).show();
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



    private void Anhxa() {

        toolbar = findViewById(R.id.toolbar_contact);
        callphone = findViewById(R.id.callphone);
        facebook = findViewById(R.id.facebook);
        zalo = findViewById(R.id.zalo);
    }
}