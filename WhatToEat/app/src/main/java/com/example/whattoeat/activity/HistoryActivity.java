package com.example.whattoeat.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class HistoryActivity extends AppCompatActivity {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private int historynum = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        catchData();
    }

    private void catchData(){
        String catchData = "http://beeanddragonhouse.myftp.org:8087/users/as209099/getComments/";
        ProgressDialog dialog = ProgressDialog.show(this,"讀取中","請稍候",true);

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
                for (int i = 0; i< historynum; i++){
                    JSONObject jsonTemp = new JSONObject(new JSONObject(jsonObject.toString()).getString((historynum - 1 - i)+""));
                    String id = jsonTemp.getString("id");
                    String name = jsonTemp.getString("name");
                    String location = jsonTemp.getString("location");
                    String stars = jsonTemp.getString("stars");
                    String comment = jsonTemp.getString("comment");
                    String date = jsonTemp.getString("date");

                    HashMap<String,String> shopMenu = new HashMap<>();
                    shopMenu.put("id",id);
                    shopMenu.put("name",name);
                    shopMenu.put("stars",stars);
                    shopMenu.put("comment",comment);
                    shopMenu.put("date",date);

                    arrayList.add(shopMenu);

                    runOnUiThread(()->{
                        dialog.dismiss();
                        RecyclerView recyclerView;
                        MyAdapter myAdapter;
                        recyclerView = findViewById(R.id.recyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                        myAdapter = new MyAdapter();
                        recyclerView.setAdapter(myAdapter);
                    });
                }
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

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView shop_name, evaluation, date, comment;
            ImageView imv_shop;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                shop_name = itemView.findViewById(R.id.tv_shop_name);
                evaluation = itemView.findViewById(R.id.tv_star);
                date = itemView.findViewById(R.id.tv_date);
                comment = itemView.findViewById(R.id.tv_comment);
                imv_shop = itemView.findViewById(R.id.imv_shop);
            }
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_history, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.shop_name.setText(arrayList.get(position).get("name"));

            String star = "";
            for (int i = 0 ; i < Integer.parseInt(arrayList.get(position).get("stars").toString());i++){
                star += "★";
            }
            holder.evaluation.setText(star);

            holder.date.setText(arrayList.get(position).get("date"));

            holder.comment.setText(arrayList.get(position).get("comment"));

            int resId = getApplicationContext().getResources().getIdentifier("shop" + arrayList.get(position).get("id"), "drawable", getPackageName());
            holder.imv_shop.setImageResource(resId);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}
