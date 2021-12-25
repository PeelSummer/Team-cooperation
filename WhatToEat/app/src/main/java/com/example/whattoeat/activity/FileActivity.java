package com.example.whattoeat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whattoeat.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class FileActivity extends AppCompatActivity {

    EditText edt_account;
    EditText edt_phone;
    EditText edt_mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        edt_account = findViewById(R.id.edt_account);
        edt_phone = findViewById(R.id.edt_phone);
        edt_mail = findViewById(R.id.edt_mail);

        catchData();
    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.btn_addPreference:
                System.out.println("Print Output : 點擊新增偏好的按鈕");
                break;
            case R.id.btn_viewHistory:
                Intent intentNext = new Intent(this, HistoryActivity.class);
                startActivity(intentNext);
                break;
        }
    }

    //抓取資料庫資料
    private void catchData(){
        String catchData = "http://beeanddragonhouse.myftp.org:8087/users/as209099/getInformation/";

        new Thread(()->{
            try {
                URL url = new URL(catchData);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String line = in.readLine();
                StringBuffer json = new StringBuffer();
                while (line != null) {
                    json.append(line);
                    line = in.readLine();
                }
                JSONObject jsonObject = new JSONObject(new JSONObject(json.toString()).getString("response"));

                edt_account.setText(jsonObject.getString("name"));
                edt_phone.setText(jsonObject.getString("phone_number"));
                edt_mail.setText(jsonObject.getString("email"));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start();
    }

}
