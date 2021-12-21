package com.example.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import android.graphics.drawable.Drawable;

import com.example.whattoeat.R;

public class MenuActivity extends AppCompatActivity {
    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        myButton.setText("我是按鈕哈哈哈\n鍋燒喔\n又換行\n我愛換行");
    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.imgBtn_personalInfo: //個人資料
                break;
            case R.id.imgBtn_conditions: //篩選條件
                break;
            default:
                break;
        }
    }
}