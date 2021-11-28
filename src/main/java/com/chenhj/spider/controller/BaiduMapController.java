package com.chenhj.spider.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.chenhj.spider.dto.BaiduMap;
import com.chenhj.spider.dto.BaiduMapExcel;
import com.chenhj.spider.service.BaiduMapService;
import com.chenhj.spider.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
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

    @RequestMapping("/map")
    public void map(HttpServletResponse response, String url){
        String data = HttpUtil.doGet(url);
        BaiduMap content = JSONUtil.toBean(data, BaiduMap.class);

        List<BaiduMapExcel> row = content.getContent().stream().map(baiduMapService::mapDataToMapExcel).collect(Collectors.toList());

        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("provinceName", "省")
                .addHeaderAlias("cityName", "市")
                .addHeaderAlias("areaName", "区")
                .addHeaderAlias("name", "场地名称")
                .addHeaderAlias("address", "详细地址")
                .write(row, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.flush(out, true);
        writer.close();
    }
}
