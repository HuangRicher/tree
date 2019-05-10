package com.tongzhu.welfare.microservice;

import com.tongzhu.welfare.model.LoveSetting;
import com.tongzhu.welfare.model.LoveTreeInfo;
import com.tongzhu.welfare.model.LoveTreeSetting;
import com.tongzhu.welfare.model.vo.MarryLogVo;
import com.tongzhu.welfare.service.MarryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marryResource")
public class MarryResource {
	
	@Autowired
	MarryService marryService;
	
	/**
	 * 获取情缘属性
	 * @param userId
	 * @return
	 */
    @PostMapping("/getLoveSetting/{userId}")
    public LoveSetting getLoveSetting(@PathVariable("userId") String userId){
    	return marryService.getLoveSetting(userId);
    }
    
	/**
	 * 获取爱情树属性
	 * @param userId
	 * @return
	 */
    @PostMapping("/getLoveTreeSetting/{userId}")
    public LoveTreeSetting getLoveTreeSetting(@PathVariable("userId") String userId){
    	return marryService.getLoveTreeSetting(userId);
    }
    
	/**
	 * 离开婚礼现场
	 * @param userId
	 * @return
	 */
    @PostMapping("/leaveWedding/{userId}/{marryId}")
    public void leaveWedding(@PathVariable("userId") String userId,@PathVariable("marryId") String marryId){
    	marryService.leaveWedding(userId,marryId);
    }
    
	/**
	 * 获取婚礼信息
	 * @return
	 */
    @PostMapping("/getWeddingInfo/{marryId}")
    public MarryLogVo getWeddingInfo(@PathVariable("marryId") String marryId){
    	MarryLogVo marryLogVo = marryService.getWeddingInfo(marryId);
    	return marryLogVo;
    }

	/**
	 * 获取情缘属性
	 * @param userId
	 * @return
	 */
	@PostMapping("/getLoveTreeInfoObject/{loveTreeId}/{userId}")
	public LoveTreeInfo getLoveTreeInfoObject(@PathVariable("loveTreeId") String loveTreeId,@PathVariable("userId") String userId){
		return marryService.getLoveTreeInfoObject(loveTreeId,userId);
	}

	/**
	 * 给指定用户减幸福值
	 * @param userId
	 * @return
	 */
	@PostMapping("/minusHappinessByUserId/{userId}/{num}/{ringId}")
	public int minusHappinessByUserId(@PathVariable("userId") String userId,@PathVariable("num") Integer num,@PathVariable("ringId") String ringId){
		return marryService.minusHappinessByUserId(userId,num,ringId);
	}



}
