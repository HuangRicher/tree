package com.tongzhu.usergoods.service.impl;

import com.tongzhu.usergoods.domain.User;
import com.tongzhu.usergoods.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UserServiceHystrix implements UserService {
    @Override
    public User findByUserId(String userId) {
        throw new RuntimeException("error");
    }

    @Override
    public void updateCrunchies(String userId, Integer type, Integer recordValues) {
        throw new RuntimeException("error");
    }

    @Override
    public boolean addCopyExtraAward(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addCopyExpSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addExplorationSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addExplorationMonster(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addFightRankingSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addMonster(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addRoleLevelSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addFightRankingExpSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addSkillSetting(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addNPC(String fileName, MultipartFile file) {
        return false;
    }

    @Override
    public boolean addNPCAttribute(String fileName, MultipartFile file) {
        return false;
    }

}
