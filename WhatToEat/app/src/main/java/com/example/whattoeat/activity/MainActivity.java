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

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.btn_login:    //會員登入
                //拿取資料
                gotoMenu();
                break;
            case R.id.btn_visitor:  //訪客進入
                gotoMenu();
                break;
            case R.id.btn_register: //註冊頁面
                gotoRegister();
                break;
            case R.id.btn_findpass: //忘記密碼
                FindPassDialog();
                break;
            case R.id.imgBtn_GoogleIn: //Google登入
                gotoMenu();
                break;
            case R.id.imgBtn_LineIn: //Google登入
                gotoMenu();
                break;
            case R.id.imgBtn_FacebookIn: //Google登入
                gotoMenu();
                break;
            default:
                break;
        }
    }

    //前往菜單
    public void gotoMenu(){
        Intent intentMenu = new Intent(this, MenuActivity.class);
        startActivity(intentMenu);
    }

    //前往註冊
    public void gotoRegister(){
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    //忘記密碼彈跳視窗
    public void FindPassDialog() {
        DialogFragment newFragment = new FindPassDialogFragment();
        newFragment.show(getSupportFragmentManager(), "list");
    }

}