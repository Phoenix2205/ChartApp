package com.example.sonyvaio.chartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class DataInputLayout extends AppCompatActivity {
    int columns = 0;
    int rows = 0;
    LinearLayout Layout;
    List<String> xAxis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input_layout);
        Layout = (LinearLayout) findViewById(R.id.main_table);
        Intent intent = getIntent();
        columns = intent.getIntExtra("COLUMNS", -1);
        rows = intent.getIntExtra("ROWS", -1);
        Log.d("COLUMNS", String.valueOf(columns));
        Log.d("ROWS", String.valueOf(rows));
        CreateTable();

    }

    private void CreateTable() {

        for (int i = 0; i < columns; i++) {

                EditText editText = new EditText(this);
                editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                editText.setId(i);
                Layout.addView(editText);




            }

        Button btnSubmit=new Button(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<columns;i++)
                    Log.d("string"+i,(EditText)Layout.getChildAt(i).getText)
            }
        });


        }

    }

