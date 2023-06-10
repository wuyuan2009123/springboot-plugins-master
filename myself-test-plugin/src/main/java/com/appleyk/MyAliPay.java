package com.appleyk;

import com.appleyk.annotation.PluginOn;
import com.appleyk.model.User;

@PluginOn
public class MyAliPay implements IPay{
    @Override
    public String pay() {
        User user = new User("appleyk","18");
        System.out.println("自定义的插件实现支付接口 -- v55！author: "+user.getName());
        return "自定义的插件实现支付接口 -- v55！";
    }
}
