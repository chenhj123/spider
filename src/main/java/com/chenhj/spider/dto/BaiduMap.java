package com.chenhj.spider.dto;

import java.util.List;

/**
 * 百度地图数据
 * @author chenhj
 */
public class BaiduMap {

    private List<Content> content;

    public static class Content {
        private String addr;
        private String name;
        private ApiAdminInfo apiAdminInfo;

        public static class ApiAdminInfo {
            private String provName;
            private String cityName;
            private String areaName;

            public String getProvName() {
                return provName;
            }

            public void setProvName(String provName) {
                this.provName = provName;
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

        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ApiAdminInfo getApiAdminInfo() {
            return apiAdminInfo;
        }

        public void setApiAdminInfo(ApiAdminInfo apiAdminInfo) {
            this.apiAdminInfo = apiAdminInfo;
        }

    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
