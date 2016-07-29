package com.example.sonyvaio.chartapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTableFormActivity extends AppCompatActivity {
    EditText numberOfField;
    EditText chartName;
    LinearLayout linearLayoutContainer;
    Boolean isTypeString=false;
    ArrayList<Map<String,List<Float>>> list = new ArrayList<>();
    List<String> legendList =new ArrayList<>();//chu thich trong ban do
    List<String>lableXAxis=new ArrayList<>();//cai label tren truc X

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chartName=(EditText)findViewById(R.id.et_chart_name);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateTableFormActivity.this,DisplayUserChart.class);
                intent.putStringArrayListExtra("XAxis label",(ArrayList<String>) lableXAxis);
                intent.putStringArrayListExtra("Legend List",(ArrayList<String>) legendList);
                intent.putExtra("data",  list);
                intent.putExtra("Chart Name", chartName.getText().toString());
                startActivity(intent);

            }
        });
        numberOfField=(EditText)findViewById(R.id.et_field_number);
        linearLayoutContainer=(LinearLayout)findViewById(R.id.linear_layout_container);
        numberOfField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
               if( keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)){
                        GenerateField(Integer.parseInt(numberOfField.getText().toString()));
                        return true; // consume.
                    }

                return false; // pass on to other listeners.
            }
        });
       // GenerateField(Integer.parseInt(numberOfField.getText().toString()));
    }

    private void GenerateField(final int  totalField)
    {


         for (int i =0; i<totalField;i++)
         {
             EditText editText = new EditText(CreateTableFormActivity.this);
             TextView title=new TextView(CreateTableFormActivity.this);
             title.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
             //int id=0;
             if (i==0)
             {
                 title.setText("Primary field");
                 linearLayoutContainer.addView(title);
                 editText.setId(i);
                 editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                 editText.setHint("Primary field: ");
                 linearLayoutContainer.addView(editText);

                 TextView type=new TextView(CreateTableFormActivity.this);
                 type.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                 type.setText("type");
                 linearLayoutContainer.addView(type);

                 RadioGroup ll = new RadioGroup(this);
                 ll.setOrientation(LinearLayout.HORIZONTAL);
                 RadioButton radioButton1=new RadioButton(CreateTableFormActivity.this);
               //  radioButton1.setId((i * 2) + i);
                 radioButton1.setText("String");
                 radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                     @Override
                     public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                         isTypeString=b;
                     }
                 });

                 Log.d("String is check",String.valueOf(isTypeString));
                 ll.addView(radioButton1);

                 RadioButton radioButton2=new RadioButton(CreateTableFormActivity.this);
               //  radioButton2.setId((i * 2) + i);
                 radioButton2.setText("Float");
                 ll.addView(radioButton2);
                 linearLayoutContainer.addView(ll);


                 continue;
             }

             title.setText("Field");
             linearLayoutContainer.addView(title);
             editText.setInputType(InputType.TYPE_CLASS_TEXT);
             editText.setId(i);
             editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
             editText.setHint("Float type: "+(i));
             linearLayoutContainer.addView(editText);
             legendList.add(editText.getText().toString());

             if(i==totalField-1)
             {
               //  final int ID=id;
                 Button btnSubmit=new Button(CreateTableFormActivity.this);
                 btnSubmit.setText("Submit and input data");
                 linearLayoutContainer.addView(btnSubmit);
                 btnSubmit.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                      linearLayoutContainer.removeAllViews();
                         GenerateData(totalField,isTypeString );
                     }
                 });

                 break;
             }
         }
    }

    private void GenerateData(final int totalField, final boolean isTypeString)
    {

        TextView inputRow= new TextView(CreateTableFormActivity.this);
        inputRow.setText("Input number row:");
        inputRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayoutContainer.addView(inputRow);

        final EditText numberOfRow = new EditText(CreateTableFormActivity.this);
        numberOfRow.setId(totalField);
        numberOfRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        numberOfRow.setHint("Input number of row");
        numberOfRow.setInputType(InputType.TYPE_CLASS_NUMBER);
        linearLayoutContainer.addView(numberOfRow);
        numberOfRow.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!keyEvent.isShiftPressed()) {
                        int totalRows=Integer.parseInt(numberOfRow.getText().toString());
                        int ID=totalField;
                        List<List<Float>>dataListContainer=new ArrayList<List<Float>>();
                        for (int row =0; row< totalRows;row++)//qua dong
                        {
                            final List<Float>dataList=new ArrayList<Float>();
                            for (int column=0; column<totalField;column++)// qua cot
                            {

                                if (column==0)//primary field
                                {
                                    final EditText editText = new EditText(CreateTableFormActivity.this);
                                    editText.setId(ID=ID+1);
                                    editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    editText.setHint("Primary Field: "+(row));
                                    if (isTypeString) editText.setInputType(InputType.TYPE_CLASS_TEXT);
                                    else editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                    linearLayoutContainer.addView(editText);
                                    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                        @Override
                                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                            lableXAxis.add(String.valueOf(editText.getText()));
                                            return false;
                                        }
                                    });

                                    continue;
                                }

                                final EditText editText = new EditText(CreateTableFormActivity.this);
                                editText.setId(ID=ID+1);
                                editText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                editText.setHint("Field: "+(row));
                                linearLayoutContainer.addView(editText);
                                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                    @Override
                                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                                        dataList.add(Float.parseFloat(editText.getText().toString()));
                                        return false;
                                    }
                                });

                            }
                            dataListContainer.add(dataList);
                        }

                        for (int y =0;y<lableXAxis.size();y++)
                        {
                            HashMap<String,List<Float>>hashMap=new HashMap<String, List<Float>>();
                            hashMap.put(lableXAxis.get(y),dataListContainer.get(y));
                            list.add(hashMap);
                        }
                        Log.d("list hash map",String.valueOf(list.size()));


                        return true;
                    }
                }

                return false;
            }
        });

    }
}
