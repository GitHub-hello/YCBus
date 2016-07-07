package com.yinchuan.ycbus.entity;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Stations {
    private String stationId;
    private String stationName;
    private String bcId;
    private String bcStationId;
    private String geoLon;
    private String geoLat;
    private String strank;
    private String status;
    private String bus_count;
    private String bus_status;
    private int count_run;
    private int count_stop;
    private String status_run;
    private String status_stop;

    public String getStatus_run() {
        return status_run;
    }

    public void setStatus_run(String status_run) {
        this.status_run = status_run;
    }

    public String getStatus_stop() {
        return status_stop;
    }

    public void setStatus_stop(String status_stop) {
        this.status_stop = status_stop;
    }

    public int getCount_run() {
        return count_run;
    }

    public void setCount_run(int count_run) {
        this.count_run = count_run;
    }

    public int getCount_stop() {
        return count_stop;
    }

    public void setCount_stop(int count_stop) {
        this.count_stop = count_stop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBus_count() {
        return bus_count;
    }

    public void setBus_count(String bus_count) {
        this.bus_count = bus_count;
    }

    public String getBus_status() {
        return bus_status;
    }

    public void setBus_status(String bus_status) {
        this.bus_status = bus_status;
    }

    public String getBcId() {
        return bcId;
    }

    public void setBcId(String bcId) {
        this.bcId = bcId;
    }

    public String getBcStationId() {
        return bcStationId;
    }

    public void setBcStationId(String bcStationId) {
        this.bcStationId = bcStationId;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(String geoLon) {
        this.geoLon = geoLon;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStrank() {
        return strank;
    }

    public void setStrank(String strank) {
        this.strank = strank;
    }
}
