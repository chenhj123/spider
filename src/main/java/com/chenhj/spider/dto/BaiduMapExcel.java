package com.chenhj.spider.dto;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 百度地图数据Excel
 * @author chenhj
 */
public class BaiduMapExcel {

    @ExcelProperty("省份")
    private String provinceName;
    @ExcelProperty("城市")
    private String cityName;
    @ExcelProperty("区县")
    private String areaName;
    @ExcelProperty("场地名称")
    private String name;
    @ExcelProperty("详细地址")
    private String address;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
