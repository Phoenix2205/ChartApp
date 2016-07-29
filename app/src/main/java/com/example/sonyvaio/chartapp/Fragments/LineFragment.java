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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineFragment extends Fragment {


    public LineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_line, container, false);
        FrameLayout frameLayout=(FrameLayout)v.findViewById(R.id.frame_layout_container_line);
        DisplayLineChart(frameLayout);
        return v;
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static LineFragment newInstance(int sectionNumber) {
        LineFragment fragment = new LineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
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
}
