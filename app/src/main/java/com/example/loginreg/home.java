package com.example.loginreg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.loginreg.Science;

import java.util.ArrayList;


public class home extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView=(ListView)findViewById(R.id.listview);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Faculty of Science");
        arrayList.add("Faculty of Education");
        arrayList.add("Faculty of Business");

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(home.this, "You have chosen:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(home.this, Science.class);
                startActivity(intent);

                Toast.makeText(home.this, "You have chosen:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(home.this, Education.class);
                startActivity(intent1);

                Toast.makeText(home.this, "You have chosen:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(home.this, Business.class);
                startActivity(intent2);
            }
        });
    }
}