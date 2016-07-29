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
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BubbleFragment extends Fragment {


    public BubbleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bubble, container, false);
        FrameLayout frameLayout= (FrameLayout) v.findViewById(R.id.frame_layout_container_bubble);
        DisplayBuubbleChart(frameLayout);
        return v;
    }

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static BubbleFragment newInstance(int sectionNumber) {
        BubbleFragment fragment = new BubbleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
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
}
