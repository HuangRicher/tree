package com.tongzhu.treehouse.service;


import com.tongzhu.treehouse.config.FeignConfig;
import com.tongzhu.treehouse.domain.BuildingVo;
import com.tongzhu.treehouse.service.impl.BuildingServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = BuildingServiceHystrix.class)
public interface BuildingService {


    /**
     * 获取树屋升级所需人气，娱乐，环境值
     * @param treeGrade  树屋当前等级
     * @return  BuildingVo
     */
    @PostMapping(value = "/buildingResource/getTreeHouseByUserId/{treeGrade}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    BuildingVo getTreeHouseByUserId(@PathVariable("treeGrade") int treeGrade);

    /**
     * 通知发生变动
     * @param content   为json的string,目前需要传userId type
     */
    @PostMapping(value = "/buildingResource/sendMsg/{content}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void sendMQMsg(@PathVariable("content") String content);
}
