package com.tongzhu.friend.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.friend.constant.TaskConstant;
import com.tongzhu.friend.mapper.DailyTaskInfoMapper;
import com.tongzhu.friend.mapper.ext.DailyTaskInfoExtMapper;
import com.tongzhu.friend.model.DailyTaskInfo;
import com.tongzhu.friend.model.DailyTaskInfoExample;
import com.tongzhu.friend.model.DailyTaskRecord;
import com.tongzhu.friend.model.DailyTaskRecordExample;
import com.tongzhu.friend.service.DailyTaskInfoService;
import com.tongzhu.friend.service.DailyTaskRecordService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Administrator on 2018/10/31 0031.
 */
@Service
public class DailyTaskInfoServiceImpl implements DailyTaskInfoService {

    @Autowired
    private DailyTaskInfoMapper dailyTaskInfoMapper;

    @Autowired
    private DailyTaskInfoExtMapper dailyTaskInfoExtMapper;


    @Autowired
    private DailyTaskRecordService dailyTaskRecordService;

    @Override
    public List<DailyTaskInfo> getDailyTaskInfoList() {
        DailyTaskInfoExample dailyTaskInfoExample = new DailyTaskInfoExample();
        return dailyTaskInfoMapper.selectByExample(dailyTaskInfoExample);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer getUserLiveness(String userId) {
        return dailyTaskInfoExtMapper.getUserLiveness(userId, TaskConstant.TASK_RECEIVE_AWARD_YES);
    }

    @Override
    @Transactional
    public DailyTaskInfo getDailyTaskInfoById(Integer id) {
        return dailyTaskInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DailyTaskInfo> getDailyTaskInfoByType(Integer type) {
        DailyTaskInfoExample dailyTaskInfoExample = new DailyTaskInfoExample();
        dailyTaskInfoExample.createCriteria().andTypeEqualTo(type);
        List<DailyTaskInfo> dailyTaskInfoList = dailyTaskInfoMapper.selectByExample(dailyTaskInfoExample);
        return dailyTaskInfoList;
    }

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<DailyTaskInfo> dailyTaskInfoList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("格式错误");
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

        for (int r = 3; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            DailyTaskInfo dailyTaskInfo = new DailyTaskInfo();
            Integer id = new Double(row.getCell(0).getNumericCellValue()).intValue();
            String name = row.getCell(1).getStringCellValue();
            String description = row.getCell(2).getStringCellValue();

            Integer type = new Double(row.getCell(3).getNumericCellValue()).intValue();
            Integer level = new Double(row.getCell(4).getNumericCellValue()).intValue();

            Integer condition = new Double(row.getCell(5).getNumericCellValue()).intValue();
            String item = row.getCell(6).getStringCellValue();
            Integer liveness = new Double(row.getCell(7).getNumericCellValue()).intValue();
            Integer link = new Double(row.getCell(8).getNumericCellValue()).intValue();

            dailyTaskInfo.setId(id);
            dailyTaskInfo.setName(name);
            dailyTaskInfo.setDescription(description);
            dailyTaskInfo.setType(type);
            dailyTaskInfo.setCondition(condition);

            dailyTaskInfo.setLiveness(liveness);
            dailyTaskInfo.setLink(link + "");
            dailyTaskInfo.setCreateDate(new Date());
            dailyTaskInfo.setLevel(level);
            item = item.replace("[", "").replace("]", "");
            String[] split = item.split(",");
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < split.length; i++) {
                JSONObject jsonObject = JSON.parseObject(item);
                Object objId = jsonObject.get("id");
                Integer num = jsonObject.getInteger("num");
                map.put(objId + "", num);
            }
            Map award = new HashMap();
            award.put("prop", map);
            dailyTaskInfo.setAward(JSONObject.toJSONString(award));
            dailyTaskInfoList.add(dailyTaskInfo);

        }

        DailyTaskInfoExample dailyTaskInfoExample = new DailyTaskInfoExample();

        List<DailyTaskInfo> dailyTaskInfos = dailyTaskInfoMapper.selectByExample(dailyTaskInfoExample);
        List<Integer> list = new ArrayList<>();
        for (DailyTaskInfo dailyTaskInfo : dailyTaskInfos) {
            list.add(dailyTaskInfo.getId());
        }


        for (DailyTaskInfo dailyTaskInfo : dailyTaskInfoList) {
            list.remove(dailyTaskInfo.getId());
            DailyTaskInfo dailyTaskInfoById = this.getDailyTaskInfoById(dailyTaskInfo.getId());
            if (dailyTaskInfoById == null) {
                this.dailyTaskInfoMapper.insertSelective(dailyTaskInfo);
                System.out.println("增加成功" + dailyTaskInfo.getId());
            } else {
                this.dailyTaskInfoMapper.updateByPrimaryKeySelective(dailyTaskInfo);
                System.out.println("更新成功" + dailyTaskInfo.getId());
            }
        }
        for (Integer i : list) {
            this.dailyTaskInfoMapper.deleteByPrimaryKey(i);
            DailyTaskRecordExample dailyTaskRecordExample = new DailyTaskRecordExample();
            dailyTaskRecordExample.createCriteria().andDailyTaskIdEqualTo(i);
            dailyTaskInfoMapper.deleteByExample(dailyTaskInfoExample);
            System.out.println("删除成功" + i);
        }
        return notNull;

    }
}
