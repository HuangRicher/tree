package com.tongzhu.welfare.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.except.CheckException;
import com.tongzhu.welfare.constant.BuildingConstant;
import com.tongzhu.welfare.constant.MallConstant;
import com.tongzhu.welfare.constant.MarryConstant;
import com.tongzhu.welfare.domain.PropInfo;
import com.tongzhu.welfare.domain.UserGoods;
import com.tongzhu.welfare.mapper.MallMapper;
import com.tongzhu.welfare.model.Mall;
import com.tongzhu.welfare.model.MallExample;
import com.tongzhu.welfare.service.MallService;
import com.tongzhu.welfare.service.UserGoodsService;
import com.tongzhu.welfare.vo.ReceiveGoldVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MallServiceImpl implements MallService {

    @Autowired
    MallMapper mallMapper;

    @Autowired
    UserGoodsService userGoodsService;


    @Override
    public Pager<Mall> getGoodsList(String userId, int pageNum, int pageSize) {
        MallExample mallExample = new MallExample();
        mallExample.createCriteria().andStatusEqualTo(MallConstant.MALL_STATUS_GOODSON);
        mallExample.setOrderByClause(" publish_time desc");
        Page<Mall> page = PageHelper.startPage(pageNum, pageSize);
        mallMapper.selectByExample(mallExample);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public Pager<Mall> getGoodsListByType(String userId, int goodsType, int pageNum, int pageSize) {
        MallExample mallExample = new MallExample();
        mallExample.createCriteria().andStatusEqualTo(MallConstant.MALL_STATUS_GOODSON).andGoodsTypeEqualTo(goodsType);
        mallExample.setOrderByClause(" publish_time desc");
        Page<Mall> page = PageHelper.startPage(pageNum, pageSize);
        mallMapper.selectByExample(mallExample);
        return new Pager<>(page.getPageNum(), page.getPageSize(), page.getPages(), page.getTotal(), page);
    }

    @Override
    public List<ReceiveGoldVo> goodsPay(String userId, int goodsId, int num) throws Exception {
        List<ReceiveGoldVo> resultList = new ArrayList<>();
        MallExample mallExample = new MallExample();
        mallExample.createCriteria().andStatusEqualTo(MallConstant.MALL_STATUS_GOODSON).andGoodsIdEqualTo(goodsId);
        mallExample.setOrderByClause(" publish_time desc");
        List<Mall> list = mallMapper.selectByExample(mallExample);
        if (list.size() > 0) {
            Mall mall = list.get(0);
            int priceType = mall.getPriceType();
            BigDecimal price = mall.getPrice();
            int total = (int) (price.intValue() * num);
            //需要判断是否有足够的钱购买对应的商品
            if (priceType == MallConstant.MALL_PRICE_GOLD) {
                UserGoods goldCount = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_COIN);
                if (goldCount.getAmount() > total) {
                    //先扣钱，然后增加背包物品
                    UserGoods userGoods = new UserGoods();
                    userGoods.setAmount(total);
                    userGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_COIN);
                    List<UserGoods> goodsList = new ArrayList<>();
                    goodsList.add(userGoods);
                    userGoodsService.subUserGoods(userId, goodsList);

                    UserGoods adduserGoods = new UserGoods();
                    adduserGoods.setAmount(num);
                    adduserGoods.setGoodsId(String.valueOf(goodsId));
                    List<UserGoods> addgoodsList = new ArrayList<>();
                    addgoodsList.add(adduserGoods);
                    userGoodsService.addUserGoods(userId, addgoodsList);

                    ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                    receiveGoldVo.setAmount(goldCount.getAmount() - total);
                    receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_COIN));
                    receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_COIN);
                    receiveGoldVo.setType(BuildingConstant.Prop);

                    UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, String.valueOf(goodsId));
                    ReceiveGoldVo receiveGoldVo2 = new ReceiveGoldVo();
                    receiveGoldVo2.setAmount(userGoods2.getAmount());
                    receiveGoldVo2.setGoodsId(goodsId);
                    receiveGoldVo2.setId(userGoods2.getGoodsId());
                    receiveGoldVo2.setType(BuildingConstant.Prop);

                    resultList.add(receiveGoldVo);
                    resultList.add(receiveGoldVo2);
                    return resultList;
                } else {
                    return resultList;
                }
            } else if (priceType == MallConstant.MALL_PRICE_DIAMOND) {
                UserGoods goldCount = userGoodsService.getGoodsCount(userId, MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                if (goldCount.getAmount() > total) {
                    //先扣钱，然后增加背包物品
                    UserGoods subuserGoods = new UserGoods();
                    subuserGoods.setAmount(total);
                    subuserGoods.setGoodsId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                    List<UserGoods> goodsList = new ArrayList<>();
                    goodsList.add(subuserGoods);
                    userGoodsService.subUserGoods(userId, goodsList);

                    UserGoods adduserGoods = new UserGoods();
                    adduserGoods.setAmount(num);
                    adduserGoods.setGoodsId(String.valueOf(goodsId));
                    List<UserGoods> addgoodsList = new ArrayList<>();
                    addgoodsList.add(adduserGoods);
                    userGoodsService.addUserGoods(userId, addgoodsList);

                    ReceiveGoldVo receiveGoldVo = new ReceiveGoldVo();
                    receiveGoldVo.setAmount(goldCount.getAmount() - total);
                    receiveGoldVo.setGoodsId(Integer.valueOf(MarryConstant.MARRY_CONSUMABLES_DIAMOND));
                    receiveGoldVo.setId(MarryConstant.MARRY_CONSUMABLES_DIAMOND);
                    receiveGoldVo.setType(BuildingConstant.Prop);

                    UserGoods userGoods2 = userGoodsService.getGoodsCount(userId, String.valueOf(goodsId));
                    ReceiveGoldVo receiveGoldVo2 = new ReceiveGoldVo();
                    receiveGoldVo2.setAmount(userGoods2.getAmount());
                    receiveGoldVo2.setGoodsId(goodsId);
                    receiveGoldVo2.setId(userGoods2.getGoodsId());
                    receiveGoldVo2.setType(BuildingConstant.Prop);

                    resultList.add(receiveGoldVo);
                    resultList.add(receiveGoldVo2);

                    return resultList;
                } else {
                    return resultList;
                }
            } else {
                return resultList;
            }
        } else {
            return resultList;
        }


    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<Mall> mallList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new CheckException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        Mall mall;
        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            mall = new Mall();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            Integer type = new Double(row.getCell(1).getNumericCellValue()).intValue();
            Integer price = new Double(row.getCell(2).getNumericCellValue()).intValue();
            Integer currency = new Double(row.getCell(3).getNumericCellValue()).intValue();
            mall.setId(id);
            mall.setGoodsId(id);
            PropInfo propInfo = userGoodsService.getPropInfo(id);
            mall.setName(propInfo.getName());
            mall.setPrice(new BigDecimal(price));
            mall.setOldPrice(new BigDecimal(price));
            mall.setPriceType(currency);
            mall.setIsHot(0);
            mall.setLimitNum(999999999);
            mall.setSaleNum(0);
            mall.setDiscountPrice(BigDecimal.ZERO);
            mall.setCreatedTime(new Date());
            mall.setPublishTime(new Date());
            mall.setUpdateTime(new Date());
            mall.setStatus(2);
            mall.setGoodsType(type);
            mallList.add(mall);
        }
        for (Mall malls : mallList) {
            Mall mallkey = mallMapper.selectByPrimaryKey(malls.getId());
            if (mallkey == null) {
                mallMapper.insert(malls);

            } else {
                mallMapper.updateByPrimaryKey(malls);
            }

        }
        return notNull;
    }

}
