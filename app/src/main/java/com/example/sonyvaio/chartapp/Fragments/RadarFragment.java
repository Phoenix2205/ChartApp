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
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadarFragment extends Fragment {


    public RadarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_radar, container, false);
        FrameLayout frameLayout=(FrameLayout)v.findViewById(R.id.frame_layout_container_radar);
        DisplayRadarChart(frameLayout);
        return  v;
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static RadarFragment newInstance(int sectionNumber) {
        RadarFragment fragment = new RadarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
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
}
