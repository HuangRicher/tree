package com.tongzhu.welfare.mapper.ext;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tongzhu.welfare.model.vo.MarryLogVo;

public interface MarryLogExtMapper {
	
	/**
	 * 根据举行时间获取当前正在进行的婚礼数据
	 * @param stopDate
	 * @return
	 */
	List<MarryLogVo> marryLogVoList(@Param("stopDate") Date stopDate);
	
	/**
	 * 根据婚礼类型与婚礼时间获取婚礼信息
	 * @param stopDate
	 * @param marryType
	 * @return
	 */
	List<MarryLogVo> marryLogVoListForWedding(@Param("stopDate") Date stopDate,@Param("marryType") int marryType);
	
	/**
	 * 根据婚礼ID获取婚礼信息
	 * @param marryId
	 * @return
	 */
	MarryLogVo getMarryLogVoById(@Param("marryId") String marryId);
	
	/**
	 * 获取用户参加预约婚礼信息，判断是否是自己的婚礼
	 * @param stopDate
	 * @param userId
	 * @return
	 */
	List<MarryLogVo> marryLogVoListByUserId(@Param("stopDate") Date stopDate,@Param("userId") String userId);
}