package com.tongzhu.welfare.service;

import java.io.IOException;
import java.util.List;

import com.tongzhu.common.Pager;
import com.tongzhu.welfare.model.Mall;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import org.springframework.web.multipart.MultipartFile;

public interface MallService {

	/**
	 * 根据获取商城商品信息列表
	 * @param userId  用户ID
	 * @param pageSize 
	 * @param pageNum 
	 * @return  List<BuildingVo> 
	 */
	Pager<Mall> getGoodsList(String userId, int pageNum, int pageSize);

	/**
	 * 根据分类ID获取商城商品信息
	 * @param userId
	 * @param goodsType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Pager<Mall> getGoodsListByType(String userId, int goodsType, int pageNum, int pageSize);

	/**
	 * 购买商品
	 * @param userId
	 * @param goodsId
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<ReceiveGoldVo> goodsPay(String userId, int goodsId, int num) throws Exception;

	boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
