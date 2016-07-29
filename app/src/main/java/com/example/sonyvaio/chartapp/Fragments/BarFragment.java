package com.example.sonyvaio.chartapp.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.sonyvaio.chartapp.Database.DatabaseHelper;
import com.example.sonyvaio.chartapp.Model.VendorMarketShared;
import com.example.sonyvaio.chartapp.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarFragment extends Fragment {

    //List<VendorMarketShared> vendorMarketShareds;
    public BarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_bar, container, false);
        FrameLayout frameLayout= (FrameLayout) v.findViewById(R.id.frame_layout_container_bar);
        DisplayBarChart(frameLayout);
//        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
//        vendorMarketShareds=databaseHelper.getAllData();
       // DisplayLineChart(frameLayout);]
       // DisplayBuubbleChart(frameLayout);
     //   DisplayScatterChart(frameLayout);
        //DisplayRadarChart(frameLayout);
        return  v;
    }

    private void DisplayBarChart(FrameLayout frameLayout) {
            com.github.mikephil.charting.charts.BarChart barChart=new com.github.mikephil.charting.charts.BarChart(getActivity());
            List<IBarDataSet> dataSetList=new ArrayList<>();
            DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
            List<VendorMarketShared> vendorMarketShareds=databaseHelper.getAllData();
            HashMap<String, List<BarEntry>> hashMap = new HashMap<>();
            List<BarEntry>samsungEntry= new ArrayList<>();
            List<BarEntry>appleEntry= new ArrayList<>();
            List<BarEntry>huaweiEntry= new ArrayList<>();
            List<BarEntry>xiaomiEntry= new ArrayList<>();
            List<BarEntry>lenovoEntry= new ArrayList<>();
            List<String> titles=new ArrayList<>();

            for(int i =0;i<vendorMarketShareds.size();i++)
            {
                titles.add(vendorMarketShareds.get(i).getPeriod());
                samsungEntry.add(new BarEntry(vendorMarketShareds.get(i).getMarkerSharedSamsung(),i));
                appleEntry.add(new BarEntry(vendorMarketShareds.get(i).getMarkerSharedApple(),i));
                huaweiEntry.add(new BarEntry(vendorMarketShareds.get(i).getMarkerSharedSHuawei(),i));
                 xiaomiEntry.add(new BarEntry(vendorMarketShareds.get(i).getMarkerSharedXiaomi(),i));
                lenovoEntry.add(new BarEntry(vendorMarketShareds.get(i).getMarkerSharedLenovo(),i));

            }


        BarDataSet barDataSetSamsung = new BarDataSet(samsungEntry, "Samsung");
        barDataSetSamsung.setColor(Color.BLUE);
        dataSetList.add(barDataSetSamsung);

        BarDataSet barDataSetApple = new BarDataSet(appleEntry, "Apple");
        barDataSetApple.setColor(Color.CYAN);
        dataSetList.add(barDataSetApple);

        BarDataSet barDataSetHuawei = new BarDataSet(huaweiEntry, "Huawei");
        barDataSetHuawei.setColor(Color.GREEN);
        dataSetList.add(barDataSetHuawei);

        BarDataSet barDataSetXiaomi = new BarDataSet(xiaomiEntry, "Xiaomi");
        barDataSetXiaomi.setColor(Color.YELLOW);
        dataSetList.add(barDataSetXiaomi);

        BarDataSet barDataSetLenovo = new BarDataSet(lenovoEntry, "Lenovo");
        barDataSetLenovo.setColor(Color.MAGENTA);
        dataSetList.add(barDataSetLenovo);

        BarData data = new BarData(titles , dataSetList);
        barChart.setData(data);
        barChart.setDescription("Mobile Vendor Market Shared");
        barChart.animateXY(2000, 2000);
        barChart.invalidate();
        frameLayout.addView(barChart);
    }

    private void DisplayLineChart (FrameLayout frameLayout)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
        List<VendorMarketShared> vendorMarketShareds=databaseHelper.getAllData();
        List<ILineDataSet> dataSetList=new ArrayList<>();
        LineChart lineChart= new LineChart(getActivity());
        List<String> titles=new ArrayList<>();
        List<Entry>samsungEntry= new ArrayList<>();
        List<Entry>appleEntry= new ArrayList<>();
        List<Entry>huaweiEntry= new ArrayList<>();
        List<Entry>xiaomiEntry= new ArrayList<>();
        List<Entry>lenovoEntry= new ArrayList<>();

        for(int i =0;i<vendorMarketShareds.size();i++)
        {
            titles.add(vendorMarketShareds.get(i).getPeriod());
            samsungEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSamsung(),i));
            appleEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedApple(),i));
            huaweiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSHuawei(),i));
            xiaomiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedXiaomi(),i));
            lenovoEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedLenovo(),i));

        }
        LineDataSet lineDataSetSamsung = new LineDataSet(samsungEntry, "Samsung");
        lineDataSetSamsung.setColor(Color.BLUE);
        dataSetList.add(lineDataSetSamsung);

        LineDataSet lineDataSetApple = new LineDataSet(appleEntry, "Apple");
        lineDataSetApple.setColor(Color.CYAN);
        dataSetList.add(lineDataSetApple);

        LineDataSet lineDataSetHuawei = new LineDataSet(huaweiEntry, "Huawei");
        lineDataSetHuawei.setColor(Color.GREEN);
        dataSetList.add(lineDataSetHuawei);

        LineDataSet lineDataSetXiaomi = new LineDataSet(xiaomiEntry, "Xiaomi");
        lineDataSetXiaomi.setColor(Color.YELLOW);
        dataSetList.add(lineDataSetXiaomi);

        LineDataSet lineDataSetLenovo = new LineDataSet(lenovoEntry, "Lenovo");
        lineDataSetLenovo.setColor(Color.MAGENTA);
        dataSetList.add(lineDataSetLenovo);

        LineData data = new LineData(titles , dataSetList);
        lineChart.setData(data);
        lineChart.setDescription("Mobile Vendor Market Shared");
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
        frameLayout.addView(lineChart);

    }

    private void DisplayScatterChart(FrameLayout frameLayout)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
        List<VendorMarketShared> vendorMarketShareds=databaseHelper.getAllData();
        List<IScatterDataSet> dataSetList=new ArrayList<>();
        ScatterChart scatterChart= new ScatterChart(getActivity());
        List<String> titles=new ArrayList<>();
        List<Entry>samsungEntry= new ArrayList<>();
        List<Entry>appleEntry= new ArrayList<>();
        List<Entry>huaweiEntry= new ArrayList<>();
        List<Entry>xiaomiEntry= new ArrayList<>();
        List<Entry>lenovoEntry= new ArrayList<>();

        for(int i =0;i<vendorMarketShareds.size();i++)
        {
            titles.add(vendorMarketShareds.get(i).getPeriod());
            samsungEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSamsung(),i));
            appleEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedApple(),i));
            huaweiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSHuawei(),i));
            xiaomiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedXiaomi(),i));
            lenovoEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedLenovo(),i));

        }

        ScatterDataSet scatterDataSetSamsung = new ScatterDataSet(samsungEntry, "Samsung");
        scatterDataSetSamsung.setColor(Color.BLUE);
        dataSetList.add(scatterDataSetSamsung);

        ScatterDataSet scatterDataSetApple = new ScatterDataSet(appleEntry, "Apple");
        scatterDataSetApple.setColor(Color.CYAN);
        dataSetList.add(scatterDataSetApple);

        ScatterDataSet scatterDataSetHuawei = new ScatterDataSet(huaweiEntry, "Huawei");
        scatterDataSetHuawei.setColor(Color.GREEN);
        dataSetList.add(scatterDataSetHuawei);

        ScatterDataSet scatterDataSetXiaomi = new ScatterDataSet(xiaomiEntry, "Xiaomi");
        scatterDataSetXiaomi.setColor(Color.YELLOW);
        dataSetList.add(scatterDataSetXiaomi);

        ScatterDataSet scatterDataSetLenovo = new ScatterDataSet(lenovoEntry, "Lenovo");
        scatterDataSetLenovo.setColor(Color.MAGENTA);
        dataSetList.add(scatterDataSetLenovo);

        ScatterData data = new ScatterData(titles , dataSetList);
        scatterChart.setData(data);
        scatterChart.setDescription("Mobile Vendor Market Shared");
        scatterChart.animateXY(2000, 2000);
        scatterChart.invalidate();
        frameLayout.addView(scatterChart);

    }

    private void DisplayBuubbleChart(FrameLayout frameLayout)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
        List<VendorMarketShared> vendorMarketShareds=databaseHelper.getAllData();
        List<IBubbleDataSet> dataSetList=new ArrayList<>();
        BubbleChart bubbleChart= new BubbleChart(getActivity());
        List<String> titles=new ArrayList<>();
        List<BubbleEntry>samsungEntry= new ArrayList<>();
        List<BubbleEntry>appleEntry= new ArrayList<>();
        List<BubbleEntry>huaweiEntry= new ArrayList<>();
        List<BubbleEntry>xiaomiEntry= new ArrayList<>();
        List<BubbleEntry>lenovoEntry= new ArrayList<>();

        for(int i =0;i<vendorMarketShareds.size();i++)
        {
            titles.add(vendorMarketShareds.get(i).getPeriod());
            samsungEntry.add(new BubbleEntry(i,vendorMarketShareds.get(i).getMarkerSharedSamsung(),1f));
            appleEntry.add(new BubbleEntry(i,vendorMarketShareds.get(i).getMarkerSharedApple(),1f));
            huaweiEntry.add(new BubbleEntry(i,vendorMarketShareds.get(i).getMarkerSharedSHuawei(),1f));
            xiaomiEntry.add(new BubbleEntry(i,vendorMarketShareds.get(i).getMarkerSharedXiaomi(),1f));
            lenovoEntry.add(new BubbleEntry(i,vendorMarketShareds.get(i).getMarkerSharedLenovo(),1f));

        }

        BubbleDataSet bubbleDataSetSamsung = new BubbleDataSet(samsungEntry, "Samsung");
        bubbleDataSetSamsung.setColor(Color.BLUE);
        dataSetList.add(bubbleDataSetSamsung);

        BubbleDataSet bubbleDataSetApple = new BubbleDataSet(appleEntry, "Apple");
        bubbleDataSetApple.setColor(Color.CYAN);
        dataSetList.add(bubbleDataSetApple);

        BubbleDataSet bubbleDataSetHuawei = new BubbleDataSet(huaweiEntry, "Huawei");
        bubbleDataSetHuawei.setColor(Color.GREEN);
        dataSetList.add(bubbleDataSetHuawei);

        BubbleDataSet bubbleDataSetXiaomi = new BubbleDataSet(xiaomiEntry, "Xiaomi");
        bubbleDataSetXiaomi.setColor(Color.YELLOW);
        dataSetList.add(bubbleDataSetXiaomi);

        BubbleDataSet bubbleDataSetLenovo = new BubbleDataSet(lenovoEntry, "Lenovo");
        bubbleDataSetLenovo.setColor(Color.MAGENTA);
        dataSetList.add(bubbleDataSetApple);

        BubbleData data = new BubbleData(titles , dataSetList);
        bubbleChart.setData(data);
        bubbleChart.setDescription("Mobile Vendor Market Shared");
        bubbleChart.animateXY(2000, 2000);
        bubbleChart.invalidate();
        frameLayout.addView(bubbleChart);

    }

    private void DisplayRadarChart(FrameLayout frameLayout)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getActivity());
        List<VendorMarketShared> vendorMarketShareds=databaseHelper.getAllData();
        List<IRadarDataSet> dataSetList=new ArrayList<>();
        RadarChart radarChart= new RadarChart(getActivity());
        List<String> titles=new ArrayList<>();
        List<Entry>samsungEntry= new ArrayList<>();
        List<Entry>appleEntry= new ArrayList<>();
        List<Entry>huaweiEntry= new ArrayList<>();
        List<Entry>xiaomiEntry= new ArrayList<>();
        List<Entry>lenovoEntry= new ArrayList<>();

        for(int i =0;i<vendorMarketShareds.size();i++)
        {
            titles.add(vendorMarketShareds.get(i).getPeriod());
            samsungEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSamsung(),i));
            appleEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedApple(),i));
            huaweiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedSHuawei(),i));
            xiaomiEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedXiaomi(),i));
            lenovoEntry.add(new Entry(vendorMarketShareds.get(i).getMarkerSharedLenovo(),i));

        }
        RadarDataSet radarDataSetSamsung = new RadarDataSet(samsungEntry, "Samsung");
        radarDataSetSamsung.setColor(Color.BLUE);
        dataSetList.add(radarDataSetSamsung);

        RadarDataSet radarDataSetApple = new RadarDataSet(appleEntry, "Apple");
        radarDataSetApple.setColor(Color.CYAN);
        dataSetList.add(radarDataSetApple);

        RadarDataSet radarDataSetHuawei = new RadarDataSet(huaweiEntry, "Huawei");
        radarDataSetHuawei.setColor(Color.GREEN);
        dataSetList.add(radarDataSetHuawei);

        RadarDataSet radarDataSetXiaomi = new RadarDataSet(xiaomiEntry, "Xiaomi");
        radarDataSetXiaomi.setColor(Color.YELLOW);
        dataSetList.add(radarDataSetXiaomi);

        RadarDataSet radarDataSetLenovo = new RadarDataSet(lenovoEntry, "Lenovo");
        radarDataSetLenovo.setColor(Color.MAGENTA);
        dataSetList.add(radarDataSetLenovo);

        RadarData data = new RadarData(titles , dataSetList);
        radarChart.setData(data);
        radarChart.setDescription("Mobile Vendor Market Shared");
        radarChart.animateXY(2000, 2000);
        radarChart.invalidate();
        frameLayout.addView(radarChart);

    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BarFragment newInstance(int sectionNumber) {
        BarFragment fragment = new BarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


 }
