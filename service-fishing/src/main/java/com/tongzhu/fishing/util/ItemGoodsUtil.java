package com.tongzhu.fishing.util;

import com.alibaba.fastjson.JSONPath;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ItemGoodsUtil {

    public static String getNameByGoodsId(String goodsId){
        try {
            ClassPathResource classPathResource = new ClassPathResource("item.json");
            String areaData =  IOUtils.toString(new InputStreamReader(classPathResource.getInputStream(),"UTF-8"));
            List<String> districtNames = (List<String>)JSONPath.read(areaData, "$[?(@.id = '" + goodsId + "')].name");
            return districtNames.size()>0?districtNames.get(0):"";
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
