package com.tongzhu.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.tongzhu.except.CheckException;
import com.tongzhu.user.mapper.CopyExtraAwardMapper;
import com.tongzhu.user.mapper.ext.CopyExtraAwardExtMapper;
import com.tongzhu.user.model.CopyExtraAward;
import com.tongzhu.user.model.CopyExtraAwardExample;
import com.tongzhu.user.service.CopyExtraAwardService;
import com.tongzhu.user.service.vo.CopyExtraAwardVO;
import com.tongzhu.util.RandomUtil;
import com.tongzhu.util.UUIDUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CopyExtraAwardServiceImpl implements CopyExtraAwardService {

    @Autowired
    private CopyExtraAwardExtMapper copyExtraAwardExtMapper;

    @Autowired
    private CopyExtraAwardMapper copyExtraAwardMapper;



    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {

        boolean notNull = false;
        List<CopyExtraAward> awardList = new ArrayList<>();
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
        if(sheet!=null){
            notNull = true;
        }
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            CopyExtraAward award = new CopyExtraAward();
            Integer level=(int)(row.getCell(0).getNumericCellValue());
            String name=row.getCell(1).getStringCellValue();
            Integer amount=(int)(row.getCell(2).getNumericCellValue());
            String goodsId=(int)row.getCell(3).getNumericCellValue()+"";
            float rate=(float) (row.getCell(4).getNumericCellValue());
            String consumeGoods=row.getCell(5).getStringCellValue();

            award.setId(UUIDUtil.generateUUID());
            award.setLevel(level);
            award.setName(name);
            award.setAmount(amount);
            award.setGoodsId(goodsId);
            award.setRate(rate);
            award.setConsumeGoods(consumeGoods);
            awardList.add(award);
        }
        CopyExtraAwardExample example=new CopyExtraAwardExample();
        copyExtraAwardMapper.deleteByExample(example);
        copyExtraAwardExtMapper.batchInsert(awardList);
        return notNull;
    }

    @Override
    public List<CopyExtraAwardVO> getExtraAwardList() {
        List<CopyExtraAwardVO> awardList=new ArrayList<>();
        CopyExtraAwardExample example=new CopyExtraAwardExample();
        example.setOrderByClause(" level_ ");
        List<CopyExtraAward> list=copyExtraAwardMapper.selectByExample(example);
        if(list!=null && !list.isEmpty()){
            int rand1=RandomUtil.random(1,1000);
            int rand2=RandomUtil.random(1,1000);
            int rand3=RandomUtil.random(1,1000);
            int temp1=0;
            int temp2=0;
            int temp3=0;
            boolean isBreak1=false;
            boolean isBreak2=false;
            boolean isBreak3=false;
            for(CopyExtraAward award:list){
                switch (award.getLevel()){
                    case 1:
                        int tmp1=temp1;
                        temp1+=award.getRate()*1000;
                        if(!isBreak1 && rand1>tmp1 && rand1<=temp1){
                            isBreak1=true;
                            CopyExtraAwardVO copyExtraAwardVO=new CopyExtraAwardVO();
                            copyExtraAwardVO.setId(award.getId());
                            copyExtraAwardVO.setAmount(award.getAmount());
                            copyExtraAwardVO.setLevel(award.getLevel());
                            copyExtraAwardVO.setGoodsId(award.getGoodsId());
                            copyExtraAwardVO.setConsumeGoods(JSON.parseObject(award.getConsumeGoods()));
                            awardList.add(copyExtraAwardVO);
                        }
                        break;
                    case 2:
                        int tmp2=temp2;
                        temp2+=award.getRate()*1000;
                        if(!isBreak2 && rand2>tmp2 && rand2<=temp2){
                            isBreak2=true;
                            CopyExtraAwardVO copyExtraAwardVO=new CopyExtraAwardVO();
                            copyExtraAwardVO.setId(award.getId());
                            copyExtraAwardVO.setAmount(award.getAmount());
                            copyExtraAwardVO.setLevel(award.getLevel());
                            copyExtraAwardVO.setGoodsId(award.getGoodsId());
                            copyExtraAwardVO.setConsumeGoods(JSON.parseObject(award.getConsumeGoods()));
                            awardList.add(copyExtraAwardVO);
                        }
                        break;
                    case 3:
                        int tmp3=temp3;
                        temp3+=award.getRate()*1000;
                        if(!isBreak3 && rand3>tmp3 && rand3<=temp3){
                            isBreak3=true;
                            CopyExtraAwardVO copyExtraAwardVO=new CopyExtraAwardVO();
                            copyExtraAwardVO.setId(award.getId());
                            copyExtraAwardVO.setAmount(award.getAmount());
                            copyExtraAwardVO.setLevel(award.getLevel());
                            copyExtraAwardVO.setGoodsId(award.getGoodsId());
                            copyExtraAwardVO.setConsumeGoods(JSON.parseObject(award.getConsumeGoods()));
                            awardList.add(copyExtraAwardVO);
                        }
                        break;
                }
            }
        }
        return awardList;
    }

    @Override
    public CopyExtraAward getById(String id) {
        return copyExtraAwardMapper.selectByPrimaryKey(id);
    }
}
