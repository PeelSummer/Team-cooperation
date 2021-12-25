package com.example.whattoeat.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.whattoeat.R;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.imgBtn_navigate: //導航
                Uri location = Uri.parse("geo:0,0?q=炸魂雞排 高雄市楠梓區楠梓路188號");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.tv_evaluation_more: //更多評價
                Intent intentNext = new Intent(this, EvaluationActivity.class);
                startActivity(intentNext);
                break;
            default:
                break;
        }
    }
}
