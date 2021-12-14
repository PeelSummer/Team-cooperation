package com.example.whattoeat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.whattoeat.dialog.FindPassDialogFragment;
import com.example.whattoeat.R;


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