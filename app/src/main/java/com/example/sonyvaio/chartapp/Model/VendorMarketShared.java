package com.example.sonyvaio.chartapp.Model;

/**
 * Created by Sony Vaio on 7/25/2016.
 */
public class VendorMarketShared {

    private String period;
    private float markerSharedSamsung;
    private float markerSharedApple;
    private float markerSharedSHuawei;
    private float markerSharedXiaomi;
    private float markerSharedLenovo;

    public float getMarkerSharedSamsung() {
        return markerSharedSamsung;
    }

    public void setMarkerSharedSamsung(float markerSharedSamsung) {
        this.markerSharedSamsung = markerSharedSamsung;
    }

    public float getMarkerSharedApple() {
        return markerSharedApple;
    }

    public void setMarkerSharedApple(float markerSharedApple) {
        this.markerSharedApple = markerSharedApple;
    }

    public float getMarkerSharedLenovo() {
        return markerSharedLenovo;
    }

    public void setMarkerSharedLenovo(float markerSharedLenovo) {
        this.markerSharedLenovo = markerSharedLenovo;
    }

    public float getMarkerSharedXiaomi() {
        return markerSharedXiaomi;
    }

    public void setMarkerSharedXiaomi(float markerSharedXiaomi) {
        this.markerSharedXiaomi = markerSharedXiaomi;
    }

    public float getMarkerSharedSHuawei() {
        return markerSharedSHuawei;
    }

    public void setMarkerSharedSHuawei(float markerSharedSHuawei) {
        this.markerSharedSHuawei = markerSharedSHuawei;
    }

    public VendorMarketShared (String period,float markerSharedSamsung,float markerSharedApple,
                               float markerSharedSHuawei, float markerSharedXiaomi,float markerSharedLenovo)
    {
        this.period=period;
        this.markerSharedSamsung =markerSharedSamsung;
        this.markerSharedApple=markerSharedApple;
        this.markerSharedSHuawei=markerSharedSHuawei;
        this.markerSharedXiaomi=markerSharedXiaomi;
        this.markerSharedLenovo=markerSharedLenovo;
    }

    public VendorMarketShared()
    {
        this("",0.0f,0.0f,0.0f,0.0f,0.0f);
    }


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


}