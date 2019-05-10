package com.tongzhu.servicemessage.service;

import com.tongzhu.except.CheckException;
import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.service.impl.TreeHouseServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.tree-house}",configuration = FeignConfig.class,fallback = TreeHouseServiceHystrix.class)
public interface TreeHouseService {


    /**
     * 进入树屋
     * @param userId
     * @param treeHouseUserId
     */
    @RequestMapping(value = "/treeHouseResource/comeInTreeHouse/{userId}/{treeHouseUserId}", method = RequestMethod.POST,
                   consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void comeInTreeHouse(@PathVariable("userId")String userId, @PathVariable("treeHouseUserId") String treeHouseUserId) throws CheckException;


    /**
     * 离开树屋
     * @param userId
     * @param treeHouseUserId
     */
    @RequestMapping(value = "/treeHouseResource/outTreeHouse/{userId}/{treeHouseUserId}",method = RequestMethod.POST,
                   consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void outTreeHouse(@PathVariable("userId")String userId,@PathVariable("treeHouseUserId") String treeHouseUserId);

}
