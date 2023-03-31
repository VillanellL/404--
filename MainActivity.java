package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kotlinx.coroutines.InternalCoroutinesApi;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //存储数据
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取各个数据信息
        final EditText et_1ET = (EditText) findViewById(R.id.et_1);
        final EditText et_2ET = (EditText) findViewById(R.id.et_2);
        final EditText et_3ET = (EditText) findViewById(R.id.et_3);
        final EditText et_4ET = (EditText) findViewById(R.id.et_4);
        final EditText et_5ET = (EditText) findViewById(R.id.et_5);
        final EditText et_6ET = (EditText) findViewById(R.id.et_6);
        //获得按钮控件
        Button login = (Button) findViewById(R.id.btn_save);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把获取到信息利用起来
                String et_1 = et_1ET.getText().toString();
                String et_2 = et_2ET.getText().toString();
                String et_3 = et_3ET.getText().toString();
                String et_4 = et_4ET.getText().toString();
                String et_5 = et_5ET.getText().toString();
                String et_6 = et_6ET.getText().toString();
                FileOutputStream fos = null;
                //抛出异常
                try {
                    fos = openFileOutput("login", MODE_PRIVATE);
                    //把这些信息写入
                    fos.write((et_1 + "\r\n" + et_2 + "\r\n" + et_3 + "\r\n" + et_4 + "\r\n" + et_5 + "\r\n" + et_6).getBytes());
                    fos.flush();//刷新
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

}