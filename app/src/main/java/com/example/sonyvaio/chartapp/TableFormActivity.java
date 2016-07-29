package com.example.sonyvaio.chartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TableFormActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText et_Column;
    EditText et_Rows;
    Button btn_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_form);
        linearLayout=(LinearLayout)findViewById(R.id.linear_layout_container);
        et_Column=(EditText)findViewById(R.id.et_columns);
        et_Rows=(EditText)findViewById(R.id.et_rows);
        btn_Submit=(Button)findViewById(R.id.btn_submit);
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cols=Integer.parseInt(et_Column.getText().toString());
                int rows=Integer.parseInt(et_Rows.getText().toString());

                Intent intent=new Intent(TableFormActivity.this,DataInputLayout.class);
                intent.putExtra("COLUMNS",cols);
                intent.putExtra("ROWS",rows);
                startActivity(intent);
            }
        });



    }
}
