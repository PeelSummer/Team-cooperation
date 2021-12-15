package com.example.whattoeat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.example.whattoeat.dialog.FindPassDialogFragment;
import com.example.whattoeat.R;

public class MainActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.edt_account);
        password = findViewById(R.id.edt_password);
    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.btn_login:    //會員登入
                //比對帳戶是否存在
                checkAccount();
                break;
            case R.id.btn_visitor:  //訪客進入
                gotoNextActivity(MenuActivity.class);
                break;
            case R.id.btn_register: //註冊頁面
                gotoNextActivity(RegisterActivity.class);
                break;
            case R.id.btn_findpass: //忘記密碼
                FindPassDialog();
                break;
            case R.id.imgBtn_GoogleIn: //Google登入
            case R.id.imgBtn_LineIn: //Line登入
            case R.id.imgBtn_FacebookIn: //Facebook登入
                externalLogin(view);
                break;
            default:
                break;
        }
    }

    //檢查登入帳號密碼
    public void checkAccount(){
        if( account.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(this,"請確實填寫帳號密碼",Toast.LENGTH_LONG).show();
        }else{
            if(true){
                gotoNextActivity(MenuActivity.class);
                Toast.makeText(getApplicationContext(),"登入成功!",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"帳號或密碼不正確，請重新輸入",Toast.LENGTH_LONG).show();
            }
        }
    }

    //外部登入
    public void externalLogin(View view){
        boolean loginSuccess = false;
        switch (view.getId()){
            case R.id.imgBtn_GoogleIn: //Google登入
                if(true){
                    loginSuccess = true;
                }
                break;
            case R.id.imgBtn_LineIn: //Line登入
                if(true){
                    loginSuccess = true;
                }
                break;
            case R.id.imgBtn_FacebookIn: //Facebook登入
                if(true){
                    loginSuccess = true;
                }
                break;
        }

        if(loginSuccess){
            gotoNextActivity(MenuActivity.class);
            Toast.makeText(getApplicationContext(),"登入成功!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"登入失敗",Toast.LENGTH_LONG).show();
        }

    }

    //前往下一個畫面
    public void gotoNextActivity(Class nextActiviy){
        Intent intentNext = new Intent(this, nextActiviy);
        startActivity(intentNext);
    }

    //忘記密碼彈跳視窗
    public void FindPassDialog() {
        DialogFragment newFragment = new FindPassDialogFragment();
        newFragment.show(getSupportFragmentManager(), "list");
    }

}