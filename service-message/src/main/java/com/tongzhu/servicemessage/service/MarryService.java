package com.tongzhu.servicemessage.service;

import com.tongzhu.servicemessage.conf.FeignConfig;
import com.tongzhu.servicemessage.domain.MarryLogVo;
import com.tongzhu.servicemessage.service.impl.MarryServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "${feign-service.welfare}",configuration = FeignConfig.class,fallback = MarryServiceHystrix.class)
public interface MarryService {


    @PostMapping(value = "/marryResource/leaveWedding/{userId}/{marryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    void leaveWedding(@PathVariable("userId") String userId, @PathVariable("marryId") String marryId);

    /**
     * 获取婚礼信息
     * @return
     */
    @PostMapping(value = "/marryResource/getWeddingInfo/{marryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    MarryLogVo getWeddingInfo(@PathVariable("marryId") String marryId);
}
