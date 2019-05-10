package com.tongzhu.user.service;

import com.tongzhu.user.model.FightRankingSetting;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FightRankingSettingService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;

    FightRankingSetting getByRankIdAndGrade(Integer ranking, Integer grade);

    FightRankingSetting getByScore(Integer score);
}
