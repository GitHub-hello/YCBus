package com.yinchuan.ycbus.entity;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RunningBus {
    private String position;
    private String lon;
    private String lat;
    private String vehicleId;
    private String status;
    private String strank;
    private String time;
    private String bcStationId;
    private String timen;
    private String flag;
    private String curStationId;
    private String avgSpeed;
    private String stationName;

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public String getBcStationId() {
        return bcStationId;
    }

    public void setBcStationId(String bcStationId) {
        this.bcStationId = bcStationId;
    }

    public String getCurStationId() {
        return curStationId;
    }

    public void setCurStationId(String curStationId) {
        this.curStationId = curStationId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStrank() {
        return strank;
    }

    public void setStrank(String strank) {
        this.strank = strank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimen() {
        return timen;
    }

    public void setTimen(String timen) {
        this.timen = timen;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
