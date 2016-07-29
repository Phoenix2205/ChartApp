package com.example.sonyvaio.chartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplayUserChart extends AppCompatActivity {
    String chartName="";
    List<String>xAxisLabel=new ArrayList<>();
    List<String> legendList =new ArrayList<>();
    ArrayList<HashMap<String,List<Float>>> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_chart);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout_user_chart);
        Intent intent = getIntent();
        list= (ArrayList<HashMap<String, List<Float>>>) intent.getSerializableExtra("data");
        Log.d("data list size",String.valueOf(list.size()));
        chartName=intent.getStringExtra("Chart Name");
        Log.d("Chart Name",String.valueOf(chartName));
        xAxisLabel=intent.getStringArrayListExtra("XAxis label");
        Log.d("xAxixLabel",String.valueOf(xAxisLabel.size()));
        legendList=intent.getStringArrayListExtra("Legend List");
        Log.d("legend list size",String.valueOf(legendList.size()));



    }

}