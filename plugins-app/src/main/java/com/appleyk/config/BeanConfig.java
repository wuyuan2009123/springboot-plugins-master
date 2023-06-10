package com.appleyk.config;

import com.appleyk.loader.HotClassLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

/**
 * <p>bean配置</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on  下午9:30 2022/11/23
 */
@Configuration
public class BeanConfig {

    @Value("${plugins.path}")
    private String path;

    @PostConstruct
    public void init(){
        HotClassLoader.PLUGINS_DIR = path;
    }

    @Bean
    /** 配置成原型（多例），主要是为了更新jar时，使用新的类加载器实例去加载*/
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public HotClassLoader hotClassLoader(){
        //如果达成jar包 那么就是 spring自己的加载器来加载 classpath， 在idea 中就是 应用程序加载器来加载
        System.out.println("get hotClassLoader parent 1:"+this.getClass().getClassLoader().getClass().getName());
        System.out.println("get hotClassLoader parent 2 :"+this.getClass().getClassLoader().getParent().getClass().getName());
        return new HotClassLoader(this.getClass().getClassLoader());
    }
}
