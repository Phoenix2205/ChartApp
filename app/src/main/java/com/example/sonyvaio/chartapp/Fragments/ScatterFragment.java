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
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScatterFragment extends Fragment {


    public ScatterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_scatter, container, false);
        FrameLayout frameLayout=(FrameLayout)v.findViewById(R.id.frame_layout_container_scatter);
        DisplayScatterChart(frameLayout);
        return v;
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static ScatterFragment newInstance(int sectionNumber) {
        ScatterFragment fragment = new ScatterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
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
}
