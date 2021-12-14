package com.example.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
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
        //這行我還不知道為什麼一定要有 我也有試圖拿掉 但會影響到後面取視窗寬度的結果
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //取得視窗的寬度
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        //這邊是將Button放進LinearLayout
        //因為要取得拿來放Button的LinearLayout 所以要記得給LinearLayout加上id(從Design設定就可以了)
        LinearLayout linlayout = (LinearLayout)findViewById(R.id.lin_menu);
        //LinearLayout提供的addView可以將Button放進去 但是得要設定好要放的按鈕的長寬 這裡用lp設定
        //-50的部分單純是我放進手機裡看起來舒服 你可以再調w
        LayoutParams lp = new LayoutParams(screenWidth-50, LayoutParams.WRAP_CONTENT);
        linlayout.addView(myButton, lp);

        //這邊是設定Button放圖片的相關內容 因為沒有圖片所以暫時先去get內建的圖標
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        //要放進Button的圖必須設定大小 否則不會顯示
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        //最後將用.setCompoundDrawables放進按鈕李 看是要放在哪邊就照下面那樣 左上右下 要放的塞圖片 不要的直接null
        myButton.setCompoundDrawables(drawable,null,null,null);

        //最後這是快樂加文字 \n可以換行
        myButton.setText("我是按鈕哈哈哈\n鍋燒喔\n又換行\n我愛換行");

        //另外LinearLayout在Attributes裡面有個gravity 可以設定按鈕要靠哪裡對齊

        //取得要調整的Button的id
        ImageButton imgBtn_personalInfo = (ImageButton) findViewById(R.id.imgBtn_personalInfo);
        imgBtn_personalInfo.setLeft(100);
        imgBtn_personalInfo.setBottom(100);

        //取得要調整的Button的id
        ImageButton imgBtn_conditions = (ImageButton) findViewById(R.id.imgBtn_conditions);
        imgBtn_conditions.setRight(100);
        imgBtn_conditions.setBottom(100);
    }
}