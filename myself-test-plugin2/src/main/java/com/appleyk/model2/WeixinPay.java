package com.appleyk.model2;

import com.appleyk.IPay;
import com.appleyk.annotation.PluginOn;

@PluginOn
public class WeixinPay implements IPay {
    @Override
    public String pay() {
        User2 user = new User2("appleyk","18");
        System.out.println("自定义的插件实现weixin接口 -- v5xxx！author: "+user.getName());
        return "自定义的插件实现支付接口 -- v5xxx！";
    }
}
