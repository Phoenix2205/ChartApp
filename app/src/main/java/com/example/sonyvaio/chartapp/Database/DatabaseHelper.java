package com.example.sonyvaio.chartapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sonyvaio.chartapp.Model.VendorMarketShared;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Sony Vaio on 7/25/2016.
 */
public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME="SmartphoneMarketShare.sqlite";
    private static final String TABLE_NAME="VendorMarketShared";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public  ArrayList<VendorMarketShared> getAllData()
    {
        ArrayList <VendorMarketShared> dataList=new ArrayList <VendorMarketShared>();//danh sach dap an
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from " +TABLE_NAME +" order by Period",null);
        if(c.moveToFirst())
        {
            do{
                String perioid=c.getString(0);
                float Samsung=c.getFloat(1);
                float Apple=c.getFloat(2);
                float Huawei=c.getFloat(3);
                float Xiaomi=c.getFloat(4);
                float Lenovo=c.getFloat(5);

                VendorMarketShared vendorMarketShared=new VendorMarketShared(perioid,Samsung,Apple,Huawei,Xiaomi,Lenovo);
                dataList.add(vendorMarketShared);
            }while(c.moveToNext());
        }

        return dataList;
    }


}
