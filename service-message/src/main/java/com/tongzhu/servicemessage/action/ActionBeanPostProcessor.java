package com.tongzhu.servicemessage.action;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ActionBeanPostProcessor implements BeanPostProcessor  {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods=bean.getClass().getMethods();
        for (Method method : methods) {
            ActionMap actionMap=method.getAnnotation(ActionMap.class);
            if(actionMap!=null){
                Action action=new Action();
                action.setMethod(method);
                action.setObject(bean);
                ActionMapUtil.put(actionMap.key(), action);
            }
        }
        return bean;
    }

}

