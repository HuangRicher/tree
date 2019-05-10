package com.tongzhu.servicemessage.service.impl;

import com.tongzhu.servicemessage.service.TaskMessageService;
import org.springframework.stereotype.Component;

@Component
public class TaskMessageServiceHystrix implements TaskMessageService {




    @Override
    public int accomplishTaskCount(String userId) {
        return 0;
    }

    @Override
    public int accomplishTaskBranchCount(String userId) {
        return 0;
    }
}
