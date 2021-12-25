package com.example.whattoeat.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whattoeat.R;

import java.util.ArrayList;

public class EvaluationActivity extends AppCompatActivity {

    private Spinner show_name;
    private int star_num = 3;
    private TextView tv_star_num;
    private EditText edit_evaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        tv_star_num = findViewById(R.id.tv_star);
        edit_evaluation = findViewById(R.id.edit_evaluation);

        show_name = findViewById(R.id.spinner_show_name);

        ArrayList arrayList = new ArrayList<Integer>();
        arrayList.add("as209099");
        arrayList.add("匿名使用者");

        ArrayAdapter adapter = new  ArrayAdapter(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,arrayList);
        show_name.setAdapter(adapter);
    }

    public void eventListener(View view){
        switch (view.getId()){
            case R.id.imgBtn_add:
                if(star_num<5){
                    star_num ++;
                    tv_star_num.setText(star_num + "★");
                }
                break;
            case R.id.imgBtn_decrease:
                if(star_num<5){
                    star_num --;
                    tv_star_num.setText(star_num + "★");
                }
                break;
        }
    }
}
