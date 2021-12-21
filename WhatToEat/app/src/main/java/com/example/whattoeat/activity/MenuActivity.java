package com.example.whattoeat.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.example.whattoeat.R;

public class MenuActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;
    private Drawable drawable;
    private static boolean isLogin;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = this.getIntent();
        isLogin = intent.getBooleanExtra("login_status", false);

        drawerLayout = findViewById(R.id.myDrawerLayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        /*
        //新增Button
        Button myButton = new Button(this);

        //以下是計算視窗寬度的程式碼
        //定義DisplayMetrics 物件
        DisplayMetrics dm = new DisplayMetrics();
        //取得視窗屬性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //取得視窗的寬度
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        //這邊是將Button放進LinearLayout
        LinearLayout linlayout = (LinearLayout)findViewById(R.id.lin_menu);
        LayoutParams lp = new LayoutParams(screenWidth-50, LayoutParams.WRAP_CONTENT);
        linlayout.addView(myButton, lp);

        //這邊是設定Button放圖片的相關內容
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        //要放進Button的圖必須設定大小 否則不會顯示
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //最後將用.setCompoundDrawables放進按鈕裡 看是要放在哪邊就照下面那樣 左上右下 要放的塞圖片 不要的直接null
        myButton.setCompoundDrawables(drawable,null,null,null);
        //加入Btn文字
        myButton.setText("我是按鈕哈哈哈\n鍋燒喔\n又換行\n我愛換行");*/
    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.imgBtn_personalInfo: //個人資料
                if(isLogin){
                    gotoNextActivity(FileActivity.class);
                }else{
                    LoginDialog();
                }
                break;
            case R.id.imgBtn_conditions: //篩選條件
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            default:
                break;
        }
    }

    //切畫畫面
    public void gotoNextActivity(Class nextActiviy){
        Intent intentNext = new Intent(this, nextActiviy);
        startActivity(intentNext);
    }

    //尚未登入彈跳視窗
    private AlertDialog LoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        View view = getLayoutInflater().inflate(R.layout.dialog_login,null);
        builder.setView(view);

        Button btn_login = view.findViewById(R.id.btn_login);
        Button btn_register = view.findViewById(R.id.btn_register);
        Button btn_forgetPassword = view.findViewById(R.id.btn_forgetPassword);
        ImageButton imgBtn_GoogleIn = view.findViewById(R.id.imgBtn_GoogleIn);
        ImageButton imgBtn_LineIn = view.findViewById(R.id.imgBtn_LineIn);
        ImageButton imgBtn_FacebookIn = view.findViewById(R.id.imgBtn_FacebookIn);

        account = view.findViewById(R.id.edt_account);
        password = view.findViewById(R.id.edt_password);

        AlertDialog dialog = builder.create();
        dialog.show();

        //登入
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccount(dialog);
            }
        });

        //註冊
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNextActivity(RegisterActivity.class);
            }
        });

        //忘記密碼
        btn_forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPassDialog();
            }
        });

        //Google登入
        imgBtn_GoogleIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                externalLogin(v);
            }
        });

        //Line登入
        imgBtn_LineIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                externalLogin(v);
            }
        });

        //Facebook登入
        imgBtn_FacebookIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                externalLogin(v);
            }
        });
        return dialog;
    }

    //檢查登入帳號密碼
    public void checkAccount(AlertDialog dialog){
        if( account.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"請確實填寫帳號密碼",Toast.LENGTH_LONG).show();
        }else{
            if(true){
                Toast.makeText(getApplicationContext(),"登入成功!",Toast.LENGTH_LONG).show();
                isLogin = true;
                dialog.dismiss();
            }else{
                Toast.makeText(this,"帳號或密碼不正確，請重新輸入",Toast.LENGTH_LONG).show();
            }
        }
    }

    //忘記密碼彈跳視窗
    private AlertDialog FindPassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        View view = getLayoutInflater().inflate(R.layout.dialog_findpass,null);
        builder.setView(view);

        Button btn_sendPassword = view.findViewById(R.id.btn_sendPassword);
        EditText edt_sendDestination = view.findViewById(R.id.edt_sendDestination);

        AlertDialog dialog = builder.create();
        dialog.show();


        btn_sendPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_sendDestination.getText().toString().equals("")){
                    Toast.makeText(getApplication(),"請確實填入郵件/手機",Toast.LENGTH_LONG).show();
                }else{
                    edt_sendDestination.setText("");
                    Toast.makeText(getApplication(),"密碼已寄至您的郵件/手機",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });
        return dialog;
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
}