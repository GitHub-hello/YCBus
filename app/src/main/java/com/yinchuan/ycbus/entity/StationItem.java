package com.yinchuan.ycbus.entity;

/**
 * Created by Administrator on 2016/6/20.
 */
public class StationItem {
    private String flag;
    private String startStation;
    private String bcId;
    private String stations;
    private String busLineName;
    private String dura;
    private String bcLineId;
    private String endStation;
    private String busLineId;
    private String stationName;
    private String strank;

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getBcLineId() {
        return bcLineId;
    }

    public void setBcLineId(String bcLineId) {
        this.bcLineId = bcLineId;
    }

    public String getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(String busLineId) {
        this.busLineId = busLineId;
    }

    public String getBusLineName() {
        return busLineName;
    }

    public void setBusLineName(String busLineName) {
        this.busLineName = busLineName;
    }

    public String getDura() {
        return dura;
    }

    public void setDura(String dura) {
        this.dura = dura;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

    public String getStrank() {
        return strank;
    }

    public void setStrank(String strank) {
        this.strank = strank;
    }
}
