package com.tongzhu.friend.service.vo;

import com.alibaba.fastjson.JSONObject;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/1 0001.
 */
public class DailyTaskVO {

    /**
     * 任务id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 任务表id
     */
    private Integer dailyTaskId;

    /**
     * 任务进展
     */
    private Integer progress;
    /**
     * 任务目标
     */
    private Integer goal;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     *  完成时间
     */
    private Date finishDate;

    /**
     *  任务状态
     */
    private Integer status;
    /**
     * 是否已经领取奖励
     */
    private Integer receiveAward;
    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String description;
    /**
     * 任务奖励
     */
    private String award;
    /**
     * 任务等级限制
     */
    private Integer level;
    /**
     * 任务链接
     */
    private String link;
    /**
     * 完成任务可以获取的活跃度
     */
    private Integer liveness;
    /**
     * 任务类型
     */
    private Integer type;
    /**
     * 任务条件数量
     */
    private String condition;

    private JSONObject awardJson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDailyTaskId() {
        return dailyTaskId;
    }

    public void setDailyTaskId(Integer dailyTaskId) {
        this.dailyTaskId = dailyTaskId;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReceiveAward() {
        return receiveAward;
    }

    public void setReceiveAward(Integer receiveAward) {
        this.receiveAward = receiveAward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getLiveness() {
        return liveness;
    }

    public void setLiveness(Integer liveness) {
        this.liveness = liveness;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public JSONObject getAwardJson() {
        return awardJson;
    }

    public void setAwardJson(JSONObject awardJson) {
        this.awardJson = awardJson;
    }
}
