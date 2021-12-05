package com.example.whattoeat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //菜單
    public void loginButton(View view) {
        Intent intentMenu = new Intent(this, MenuActivity.class);
        startActivity(intentMenu);
    }

    //註冊
    public void RegisterButton(View view) {
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }



    //忘記密碼
    public void FindButton(View view) {
        switch (view.getId()){
            case R.id.btn_findpass:
                FindPassDialog();
                break;
        }

    }
    public void FindPassDialog() {
        DialogFragment newFragment = new FindPassDialogFragment();
        newFragment.show(getSupportFragmentManager(), "list");
    }

}