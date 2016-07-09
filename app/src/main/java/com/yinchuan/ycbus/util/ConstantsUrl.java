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
	/**
	 * 爬取360的美女图片分类
	 */
	public static final String GET_360_PICTURE_CATEGORY="http://image.so.com/api/channel_tags.json";

	/*爬去360美图的封面图
	* ch：beauty，
	* t1：595。。。。。。。
	* listtype：hot， new
	* sn;开始数目，默认null，30、60、90、120、150.。。。。。。
	* */
	public static final String GET_360_PICTURE_BY_HTML = "http://image.so.com/z";
	public static final String GET_360_PICTURE_BY_JSON = "http://image.so.com/zj";
	/*爬去360美图单个包含的列表数据
	*ch=beauty
	* t1=595
	* listtype=hot
	* id=2a5a8577befe928ac705feecfb1ed70b
	 **/
	public static final String GET_360_PICTURE_DETAIL = "http://image.so.com/zvj";


}
