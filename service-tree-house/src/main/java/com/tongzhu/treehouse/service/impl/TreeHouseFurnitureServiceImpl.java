package com.tongzhu.treehouse.service.impl;

import com.tongzhu.treehouse.constant.FurnitureConstant;
import com.tongzhu.treehouse.constant.TreeHouseFurnitureConstant;
import com.tongzhu.treehouse.mapper.TreeHouseFurnitureMapper;
import com.tongzhu.treehouse.model.Furniture;
import com.tongzhu.treehouse.model.TreeHouseExample;
import com.tongzhu.treehouse.model.TreeHouseFurniture;
import com.tongzhu.treehouse.model.TreeHouseFurnitureExample;
import com.tongzhu.treehouse.service.FurnitureService;
import com.tongzhu.treehouse.service.TreeHouseFurnitureService;
import com.tongzhu.util.DateComputeUtil;
import com.tongzhu.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TreeHouseFurnitureServiceImpl implements TreeHouseFurnitureService {

    @Autowired
    private TreeHouseFurnitureMapper treeHouseFurnitureMapper;

    @Autowired
    private FurnitureService furnitureService;



    @Override
    public void add(String treeHouseUserId,String goodsId) {
        Furniture furniture=furnitureService.getById(goodsId);
        if(furniture!=null){
            TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
            example.createCriteria().andTreeHouseIdEqualTo(treeHouseUserId).andGoodsIdEqualTo(furniture.getCategoryId());
            List<TreeHouseFurniture> treeHouseFurnitureList=treeHouseFurnitureMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(treeHouseFurnitureList)){
                TreeHouseFurniture treeHouseFurniture=new TreeHouseFurniture();
                treeHouseFurniture.setGoodsId(furniture.getCategoryId());
                treeHouseFurniture.setTreeHouseId(treeHouseUserId);
                treeHouseFurniture.setId(UUIDUtil.generateUUID());
                treeHouseFurniture.setStatus(TreeHouseFurnitureConstant.STATUS_COLLOCATION_NO);
                if(furniture.getType()== FurnitureConstant.STATUS_FOREVER_NO){
                    treeHouseFurniture.setLastTime(furniture.getUseableTime());
                    treeHouseFurniture.setCanForever(false);
                }
                if(furniture.getType()==FurnitureConstant.STATUS_FOREVER_YES){
                    treeHouseFurniture.setCanForever(true);
                }
                treeHouseFurnitureMapper.insert(treeHouseFurniture);
            }else{
                TreeHouseFurniture treeHouseFurniture=treeHouseFurnitureList.get(0);
                Integer lastTime=furniture.getUseableTime();
                lastTime+=furniture.getUseableTime();
                treeHouseFurniture.setLastTime(lastTime);
                treeHouseFurnitureMapper.updateByPrimaryKeySelective(treeHouseFurniture);
            }

        }
    }

    @Override
    public TreeHouseFurniture getByTreeHouseIdAndGoodsId(String treeHouseUserId, String goodsId) {
        TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseUserId).andGoodsIdEqualTo(goodsId);
        List<TreeHouseFurniture> list=treeHouseFurnitureMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public void delete(String id) {
        treeHouseFurnitureMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TreeHouseFurniture> findCollocationByTreeHouseId(String treeHouseId) {

        /*TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        TreeHouseFurnitureExample.Criteria criteria1=example.createCriteria();
        criteria1.andTreeHouseIdEqualTo(treeHouseId).andStatusEqualTo(TreeHouseFurnitureConstant.STATUS_COLLOCATION_YES)
                .andEndDateGreaterThan(new Date());
        TreeHouseFurnitureExample.Criteria criteria2=example.createCriteria();
        criteria2.andTreeHouseIdEqualTo(treeHouseId).andStartDateIsNull();
        example.or(criteria2);*/

        TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        example.createCriteria().andTreeHouseIdEqualTo(treeHouseId).andStatusEqualTo(TreeHouseFurnitureConstant.STATUS_COLLOCATION_YES)
        .andEndDateGreaterThanOrEqualTo(new Date());
        return treeHouseFurnitureMapper.selectByExample(example);
    }

    @Override
    public void arrange(String userId, String goodsId,Integer location) {
        TreeHouseFurniture treeHouseFurniture=this.getByTreeHouseIdAndGoodsId(userId,goodsId);
        Date date=new Date();
        if(treeHouseFurniture!=null){
            if(!treeHouseFurniture.getCanForever()){
                LocalDateTime dateTime=DateComputeUtil.DateToLocalDateTime(date);
                LocalDateTime nowTime=dateTime.plusSeconds(treeHouseFurniture.getLastTime());
                treeHouseFurniture.setEndDate(DateComputeUtil.localDateTimeToDate(nowTime));
            }
            treeHouseFurniture.setLocation(location);
            treeHouseFurniture.setStartDate(date);
            treeHouseFurniture.setStatus(TreeHouseFurnitureConstant.STATUS_COLLOCATION_YES);
            treeHouseFurnitureMapper.updateByPrimaryKey(treeHouseFurniture);
        }
    }

    @Override
    public List<TreeHouseFurniture> findOwnFurnitureList(String userId) {
        TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        TreeHouseFurnitureExample.Criteria criteria1=example.createCriteria();
        criteria1.andTreeHouseIdEqualTo(userId).andEndDateGreaterThan(new Date());
        TreeHouseFurnitureExample.Criteria criteria2=example.createCriteria();
        criteria2.andTreeHouseIdEqualTo(userId).andStartDateIsNull();
        example.or(criteria2);
        return treeHouseFurnitureMapper.selectByExample(example);
    }

    @Override
    public TreeHouseFurniture getById(String id) {
        return treeHouseFurnitureMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TreeHouseFurniture> findCollocations() {
        TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        example.createCriteria().andStatusEqualTo(TreeHouseFurnitureConstant.STATUS_COLLOCATION_YES).andEndDateGreaterThanOrEqualTo(new Date());
        example.setOrderByClause(" tree_house_id ");
        return treeHouseFurnitureMapper.selectByExample(example);
    }

    @Override
    public void move(String userId, List<TreeHouseFurniture> furnitures) {
        for(TreeHouseFurniture furniture:furnitures){
            TreeHouseFurniture treeHouseFurniture=this.getByTreeHouseIdAndGoodsId(userId,furniture.getGoodsId());
            if(treeHouseFurniture!=null){
                treeHouseFurniture.setLocation(furniture.getLocation());
                treeHouseFurnitureMapper.updateByPrimaryKey(treeHouseFurniture);
            }
        }

    }

    @Override
    public void cancelArrangedFurniture(String userId, String goodsId) {
        TreeHouseFurniture treeHouseFurniture=this.getByTreeHouseIdAndGoodsId(userId,goodsId);
        if(treeHouseFurniture!=null){
            treeHouseFurniture.setLocation(null);
            treeHouseFurniture.setStatus(TreeHouseFurnitureConstant.STATUS_COLLOCATION_NO);
            treeHouseFurnitureMapper.updateByPrimaryKey(treeHouseFurniture);
        }
    }

    @Override
    public void deleteOverdueFurniture() {
        TreeHouseFurnitureExample example=new TreeHouseFurnitureExample();
        example.createCriteria().andEndDateLessThan(new Date());
    }
}
