package com.tongzhu.welfare.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tongzhu.welfare.service.UserGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tongzhu.common.BaseReturn;
import com.tongzhu.common.BaseReturnCode;
import com.tongzhu.common.Pager;
import com.tongzhu.welfare.domain.User;
import com.tongzhu.welfare.model.Mall;
import com.tongzhu.welfare.po.MallPo;
import com.tongzhu.welfare.service.MallService;
import com.tongzhu.welfare.service.UserService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 商城系统相关Controller
 *
 * @author 吴恒斌
 * @date 2018年11月10日
 */
@Api(value = "商城系统controller", tags = {"商城系统API"})
@RestController
@RequestMapping(value = "/building")
public class MallController {

//    private static Logger log = LoggerFactory.getLogger(MallController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private MallService mallService;
    @Autowired
    private UserGoodsService userGoodsService;

    /**
     * 根据用户ID查找所有可出售的商品信息列表
     *
     * @param mallPo
     * @return
     */
    @ApiOperation(value = "根据用户ID查找所有可出售的商品信息列表", notes = "请求参数说明 [userId 用户ID] [pageNum 页号] "
            + "[pageSize 每页大小]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[goodsId 商品ID] [name 名称] [pic 图片] " +
                    "[price 价格 ] [oldPrice 原价][priceType 价格类型：1：金币； 2：钻石；]" +
                    "[limitNum 限购数量] [status 状态：1:草稿，2:上架，3:已下架] [goodsType 商品标签："
                    + "1：常用道具；2：树屋装扮；3：时装道具；4：宠物道具；]")
    })
    @PostMapping("/searchGoodsList")
    public BaseReturn searchGoodsList(@RequestBody MallPo mallPo) {
        if (StringUtils.isBlank(mallPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (mallPo.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (mallPo.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");
        String userId = mallPo.getUserId();
        int pageNum = mallPo.getPageNum();
        int pageSize = mallPo.getPageSize();
        User userDetail = userService.findByUserId(userId);
        if (userDetail != null) {
            Pager<Mall> list = mallService.getGoodsList(userId, pageNum, pageSize);
            return new BaseReturn("查找商城信息成功！", list);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家的信息，请重新输入ID！");
        }
    }

    /**
     * 根据用户ID查找对应类型可出售的商品信息列表
     *
     * @param mallPo
     * @return
     */
    @ApiOperation(value = "根据用户ID查找对应类型可出售的商品信息列表", notes = "请求参数说明 [userId 用户ID] [goodsType 商品标签] "
            + "[pageNum 页号] [pageSize 每页大小]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[goodsId 商品ID] [name 名称] [pic 图片] " +
                    "[price 价格 ] [oldPrice 原价][priceType 价格类型：1：金币； 2：钻石；]" +
                    "[limitNum 限购数量] [status 状态：1:草稿，2:上架，3:已下架] [goodsType 商品标签："
                    + "1：常用道具；2：树屋装扮；3：时装道具；4：宠物道具；]")
    })
    @PostMapping("/searchGoodsListByType")
    public BaseReturn searchGoodsListByType(@RequestBody MallPo mallPo) {
        if (StringUtils.isBlank(mallPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(String.valueOf(mallPo.getGoodsType())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "商品标签为空！");
        if (mallPo.getPageNum() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "页码为0！");
        if (mallPo.getPageSize() == 0)
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "每页大小为0!");
        String userId = mallPo.getUserId();
        int pageNum = mallPo.getPageNum();
        int pageSize = mallPo.getPageSize();
        int goodsType = mallPo.getGoodsType();
        User userDetail = userService.findByUserId(userId);
        if (userDetail != null) {
            Pager<Mall> list = mallService.getGoodsListByType(userId, goodsType, pageNum, pageSize);
            return new BaseReturn("查找商城信息成功！", list);
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家的信息，请重新输入ID！");
        }
    }

    /**
     * 下单购买
     *
     * @param mallPo
     * @return
     */
    @ApiOperation(value = "下单购买", notes = "请求参数说明 [userId 用户ID] [goodsId 商品ID] [num 购买数量]")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回参数说明[goodsId 商品ID] [type 类型:1:道具；2：装备；3：武器] [amout 剩余总数量]")
    })
    @PostMapping("/goodsPay")
    public BaseReturn goodsPay(@RequestBody MallPo mallPo) {
        if (StringUtils.isBlank(mallPo.getUserId()))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "用户ID为空！");
        if (StringUtils.isBlank(String.valueOf(mallPo.getGoodsId())))
            return new BaseReturn(BaseReturnCode.PARAMS_ERROR, "商品Id为空！");
        String userId = mallPo.getUserId();
        int goodsId = mallPo.getGoodsId();
        int num = mallPo.getNum();
        User userDetail = userService.findByUserId(userId);
        if (userDetail != null) {

            int backpackSpace = userGoodsService.queryBackpackSpace(userId, Stream.of(goodsId + "").collect(Collectors.toList()));
            if (backpackSpace < 0) {
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "背包空间不足！");
            }
            try {
                List<ReceiveGoldVo> receiveGoldLsit = mallService.goodsPay(userId, goodsId, num);
                return new BaseReturn("购买商品成功！", receiveGoldLsit);
            } catch (Exception e) {
                e.printStackTrace();
                return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "购买商品失败！");
            }
        } else {
            return new BaseReturn(BaseReturnCode.PROCESS_ERROR, "未查询到该名玩家的信息，请重新输入ID！");
        }
    }
}
