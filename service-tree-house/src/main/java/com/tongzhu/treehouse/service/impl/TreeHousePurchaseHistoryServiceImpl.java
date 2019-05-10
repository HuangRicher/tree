package com.tongzhu.treehouse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tongzhu.common.Pager;
import com.tongzhu.treehouse.constant.PurchaseHistoryConstant;
import com.tongzhu.treehouse.mapper.TreeHousePurchaseHistoryMapper;
import com.tongzhu.treehouse.mapper.ext.TreeHousePurchaseHistoryExtMapper;
import com.tongzhu.treehouse.mapper.vo.TreeHousePurchaseHistoryDO;
import com.tongzhu.treehouse.model.TreeHousePurchaseHistory;
import com.tongzhu.treehouse.service.TreeHousePurchaseHistoryService;
import com.tongzhu.treehouse.service.vo.TreeHousePurchaseHistoryVO;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TreeHousePurchaseHistoryServiceImpl implements TreeHousePurchaseHistoryService {

    @Autowired
    private TreeHousePurchaseHistoryMapper treeHousePurchaseHistoryMapper;

    @Autowired
    private TreeHousePurchaseHistoryExtMapper treeHousePurchaseHistoryExtMapper;

    @Override
    public int add(TreeHousePurchaseHistory treeHousePurchaseHistory) {
        return treeHousePurchaseHistoryMapper.insertSelective(treeHousePurchaseHistory);
    }

    @Override
    public Pager<TreeHousePurchaseHistoryVO> findByPage(String userId, Integer pageNum, Integer pageSize) {

        Page<TreeHousePurchaseHistoryDO> page= PageHelper.startPage(pageNum,pageSize);
        treeHousePurchaseHistoryExtMapper.purchaseHistoryList(userId);
        List<TreeHousePurchaseHistoryDO> doList=page.getResult();
        List<TreeHousePurchaseHistoryVO> list=new ArrayList<>();
        for(TreeHousePurchaseHistoryDO pdo:doList){
            TreeHousePurchaseHistoryVO vo=new TreeHousePurchaseHistoryVO();
            vo.setId(pdo.getId());

            vo.setBargainorName(pdo.getBargainorName());
            vo.setBargainorUserId(pdo.getBargainorUserId());
            vo.setCreationTime(pdo.getCreationStartDate());
            vo.setMonetaryAmount(pdo.getMonetaryAmount());
            vo.setPurchaserName(pdo.getPurchaserName());
            vo.setPurchaserUserId(pdo.getPurchaserUserId());
            vo.setSellingPrice(pdo.getSellingPrice());
            vo.setSellName(pdo.getSellName());
            vo.setSellUserId(pdo.getSellUserId());
            if (userId.equals(pdo.getSellUserId())) {
                 if (!StringUtil.isEmpty(pdo.getPurchaserName()) && !StringUtil.isEmpty(pdo.getBargainorUserId())) {
                     vo.setType(PurchaseHistoryConstant.TYPE_HASMASTER_BUY);
                     list.add(vo);
                     continue;
                 } if (!StringUtil.isEmpty(pdo.getPurchaserName()) && StringUtil.isEmpty(pdo.getBargainorUserId())) {
                     vo.setType(PurchaseHistoryConstant.TYPE_FREE_BUY);
                    list.add(vo);
                    continue;
                }
            } if (userId.equals(pdo.getBargainorUserId())) {
                vo.setType(PurchaseHistoryConstant.TYPE_WORKER_BUY);
                list.add(vo);
                continue;
            }

        }
        return new Pager<>(page.getPageNum(),page.getPageSize(),page.getPages(),page.getTotal(),list);
    }

    @Override
    public int delPurchaseHistory(String id) {
        TreeHousePurchaseHistory treeHousePurchaseHistory = treeHousePurchaseHistoryMapper.selectByPrimaryKey(id);
        treeHousePurchaseHistory.setStatus(PurchaseHistoryConstant.STATUS_REMOVE);
        return treeHousePurchaseHistoryMapper.updateByPrimaryKey(treeHousePurchaseHistory);
    }

}
