package com.yinchuan.ycbus.util;

public class ConstantsUrl {
	public static final String BASE_IP = "http://42.63.19.205:18080/yinchuanWeb/api";
	//关键字查询公交车列表
	public static final String GET_BUSLINE_LIST = BASE_IP.concat("/BusInfoService/queryBusLines.json");
	//关键字查询公交车车站
	public static final String GET_BUSSTATION_LIST = BASE_IP.concat("/BusInfoService/queryBusStations.json");
	//某路车的全部站点
	public static final String GET_BUSLINE_ALLSTATION = BASE_IP.concat("/BusInfoService/queryBusLineInfo.json");
	//某路车的全部正在路上的位置
	public static final String GET_BUS_RUNNING = BASE_IP.concat("/BusInfoService/getBusLineVehiclePos.json");
	//站点详情
	public static final String GET_BUS_STATION_DETAIL = BASE_IP.concat("/BusInfoService/queryBusLinesByStationName.json");
	//美图
	/*
	category:美女、壁纸、明星、汽车
	tag：全部
	start：0、10、20.。。。。。
	len：个数
	 */
	public static final String GET_PICTURE = "http://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp";
	/**
	 * 新闻类别
	 */
	public static final String GET_NEWS_CATEGORY = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
/**
 * 根据频道或者关键字查询新闻
 */
public static final String GET_NEWS_DETAIL = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";

}
