package com.chenhj.spider.service;

import com.chenhj.spider.dto.BaiduMap;
import com.chenhj.spider.dto.BaiduMapExcel;
import com.chenhj.spider.utils.GenericBuilder;
import org.springframework.stereotype.Service;

/**
 * @author chenhj
 */
@Service
public class BaiduMapService {

    public BaiduMapExcel mapDataToMapExcel(BaiduMap.Content content){
        return GenericBuilder.of(BaiduMapExcel::new)
                .with(BaiduMapExcel::setProvinceName, content.getApiAdminInfo().getProvName())
                .with(BaiduMapExcel::setCityName, content.getApiAdminInfo().getCityName())
                .with(BaiduMapExcel::setAreaName, content.getApiAdminInfo().getAreaName())
                .with(BaiduMapExcel::setName, content.getName())
                .with(BaiduMapExcel::setAddress, content.getAddr())
                .build();
    }
}
