package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private EditText mEtUser;
    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtUser = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);
        mBtnLogin = findViewById(R.id.btn_login);

//        mBtnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, FuncActivity1.class);
//                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        });

        mBtnLogin.setOnClickListener(this::onClick);
    }

    private void onClick(View v)
    {
        String username = mEtUser.getText().toString();
        String password = mEtPassword.getText().toString();
        if (username.equals("admin") && password.equals("000000"))
        {
            Intent intent = new Intent(MainActivity.this, FuncActivity1.class);
            Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
        }
    }
}

