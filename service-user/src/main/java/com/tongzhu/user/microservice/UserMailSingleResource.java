package com.tongzhu.user.microservice;

import com.tongzhu.user.model.UserMailSingle;
import com.tongzhu.user.service.UserMailSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userMailSingleResource")
public class UserMailSingleResource {

    @Autowired
    private UserMailSingleService userMailSingleService;


    @PostMapping("/addUserMailSingle")
    public int addUserMailSingle(@RequestBody  UserMailSingle userMailSingle){
        return userMailSingleService.insertSelective(userMailSingle);
    }

    @PostMapping("/userMailMessage/{userId}")
    public int userMailMessage(@PathVariable("userId")String userId){
        return userMailSingleService.userMailMessage(userId);
    }


}
