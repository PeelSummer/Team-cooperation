package com.example.whattoeat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class FileActivity extends AppCompatActivity {

    private int historynum = 4;

    private EditText edt_account;
    private EditText edt_phone;
    private EditText edt_mail;

    private TextView tv_shop_name;
    private TextView tv_date;
    private ImageView imv_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        edt_account = findViewById(R.id.edt_account);
        edt_phone = findViewById(R.id.edt_phone);
        edt_mail = findViewById(R.id.edt_mail);

        tv_shop_name = findViewById(R.id.tv_shop_name);
        tv_date = findViewById(R.id.tv_date);
        imv_shop = findViewById(R.id.imv_shop);

        catchUserData();
        catchHistoryData();
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
    private void catchUserData(){
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

    private void catchHistoryData(){
        String catchData = "http://beeanddragonhouse.myftp.org:8087/users/as209099/getComments/";

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
                System.out.println("Print Output : jsonObject = " + jsonObject.getString((historynum - 1 +"")));
                jsonObject = new JSONObject(new JSONObject(jsonObject.toString()).getString((historynum - 1 +"")));

                tv_shop_name.setText(jsonObject.getString("name"));
                tv_date.setText(jsonObject.getString("date"));

                int resId = getApplicationContext().getResources().getIdentifier("shop" + jsonObject.getString("id"), "drawable", getPackageName());
                imv_shop.setImageResource(resId);

            } catch (MalformedURLException e) {
                System.out.println("Print Output : MalformedURLException = " + e);
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Print Output : IOException = " + e);
                e.printStackTrace();
            } catch (JSONException e) {
                System.out.println("Print Output : JSONException = " + e);
                e.printStackTrace();
            }

        }).start();
    }
}
