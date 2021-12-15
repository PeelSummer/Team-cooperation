package com.example.whattoeat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.whattoeat.R;

import java.util.Dictionary;
import java.util.Hashtable;

public class RegisterActivity extends AppCompatActivity {

    private EditText account;
    private EditText phone;
    private EditText mail;
    private EditText password;
    private EditText passwordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = findViewById(R.id.edt_account);
        phone = findViewById(R.id.edt_phone);
        mail = findViewById(R.id.edt_mail);
        password = findViewById(R.id.edt_password);
        passwordCheck = findViewById(R.id.edt_passwordCheck);
    }

    public void eventListener(View view){
        if(checkField()){
            if(password.getText().toString().equals(passwordCheck.getText().toString())){
                addUser();
                gotoNextActivity(MainActivity.class);
                Toast.makeText(getApplicationContext(),"註冊成功!",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"密碼輸入有誤!",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(), "資料尚未填寫完成", Toast.LENGTH_LONG).show();
        }
    }

    //註冊
    private void addUser() {
            /*Dictionary data = new Hashtable();
            data.put("account", account.getText().toString());
            data.put("phone", phone.getText().toString());
            data.put("mail", mail.getText().toString());
            data.put("password", password.getText().toString());*/
    }

    // 檢查欄位是否都有填入
    private Boolean checkField() {
        String[] field = {
                account.getText().toString(),
                phone.getText().toString(),
                mail.getText().toString(),
                password.getText().toString(),
                passwordCheck.getText().toString()
        };
        for (String text : field) {
            if (text.equals("")) {
                return false;
            }
        }
        return true;
    }

    //前往下一個畫面
    public void gotoNextActivity(Class nextActiviy){
        Intent intentNext = new Intent(this, nextActiviy);
        startActivity(intentNext);
    }

}
