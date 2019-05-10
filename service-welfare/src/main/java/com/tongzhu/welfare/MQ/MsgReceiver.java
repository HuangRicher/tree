package com.tongzhu.welfare.MQ;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tongzhu.constant.RabbitMQConstant;
import com.tongzhu.welfare.service.BuildingService;
import com.tongzhu.welfare.service.UserService;

@Component
@RabbitListener(queues = "${spring.rabbitmq.queue-a}")
public class MsgReceiver {
 
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;


    @RabbitHandler
    public void process(String content) {
//        logger.info("接收处理队列A当中的消息： " + content);
        //首先检测金币数量，其次检查树屋等级是否发生变化，以及人气以及环境以及娱乐值的变化
        //树屋的人气，环境，娱乐值的变动，以及金币的变动，当前的所有建筑的等级，出树屋之后所有的建筑的升级消耗金币数是相同的，
        //对传过来的数据进行处理，区分是哪种类型，其次通过调用同居类方法来判断是否可以升级，最后，进行通知
        
        JSONObject obj = JSON.parseObject(content);
        String userId = obj.getString("userId");
        String type=obj.getString("type");
        
        if(type.equals(RabbitMQConstant.TYPE_MONEY)){
            buildingService.judgeBuildingByUserId(userId);
            userService.sendSkillRedTip(userId);
        }
        if(type.equals(RabbitMQConstant.TYPE_EXP)){
            userService.sendSkillRedTip(userId);
        }
        if(type.equals(RabbitMQConstant.TYPE_AMBIENCE)){
            buildingService.judgeBuildingByUserId(userId);
        }
        if(type.equals(RabbitMQConstant.TYPE_AMUSEMENT)){
            buildingService.judgeBuildingByUserId(userId);
        }
        if(type.equals(RabbitMQConstant.TYPE_ENVIRONMENT)){
            buildingService.judgeBuildingByUserId(userId);
        }
    }
}