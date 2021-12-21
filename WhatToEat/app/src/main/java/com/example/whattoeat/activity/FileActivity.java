package com.example.whattoeat.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whattoeat.R;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.btn_addPreference:
                System.out.println("Print Output : 點擊新增偏好的按鈕");
                break;
            case R.id.btn_viewHistory:
                System.out.println("Print Output : 點擊查看更多歷史資料");
                break;
        }
    }
}
