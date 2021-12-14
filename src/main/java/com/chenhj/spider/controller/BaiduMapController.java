package com.chenhj.spider.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.chenhj.spider.dto.BaiduMap;
import com.chenhj.spider.dto.BaiduMapExcel;
import com.chenhj.spider.service.BaiduMapService;
import com.chenhj.spider.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenhj
 */
@RestController
@RequestMapping("/baidu")
public class BaiduMapController {

    final BaiduMapService baiduMapService;
    @Autowired
    public BaiduMapController(BaiduMapService baiduMapService){
        this.baiduMapService = baiduMapService;
    }

    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public void map(HttpServletResponse response, String url){
        String data = HttpUtil.doGet(url);
        BaiduMap content = JSONUtil.toBean(data, BaiduMap.class);

        List<BaiduMapExcel> row = content.getContent().stream().filter(element -> element.getApiAdminInfo() != null).map(baiduMapService::mapDataToMapExcel).collect(Collectors.toList());

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + DateUtil.format(DateUtil.date(),"yyyyMMddHHmmss") + ".xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), BaiduMapExcel.class).sheet("模板").doWrite(row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
