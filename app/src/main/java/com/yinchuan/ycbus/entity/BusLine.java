package com.yinchuan.ycbus.entity;

import java.util.ArrayList;

public class BusLine{
	
	private String busLineId;
	private String busLineName;
	private String beginIndex;
	private String endIndex;
	private String order;
	private String stationName;
	private String startTime;
	private String flag;
	private String startStation;
	private String teminalName;
	private String endTime;
	private String endStation;
	private String originName;
	private ArrayList<Stations> stations;

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public ArrayList<Stations> getStations() {
		return stations;
	}

	public void setStations(ArrayList<Stations> stations) {
		this.stations = stations;
	}

	public String getTeminalName() {
		return teminalName;
	}

	public void setTeminalName(String teminalName) {
		this.teminalName = teminalName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
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
	public String getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(String beginIndex) {
		this.beginIndex = beginIndex;
	}
	public String getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

}
