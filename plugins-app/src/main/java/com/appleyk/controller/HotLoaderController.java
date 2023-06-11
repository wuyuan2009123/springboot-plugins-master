package com.appleyk.controller;

import com.appleyk.IPay;
import com.appleyk.loader.HotClassLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <p>jar包热加载Controller</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on  下午9:28 2022/11/23
 */
@RestController
@RequestMapping("/hot")
@RequiredArgsConstructor
public class HotLoaderController {

    private final ObjectProvider<List<IPay>> instance;

    @GetMapping("pay")
    public String pay() {
        try{
            List<IPay> ifAvailable = instance.getIfAvailable();
            if (Objects.nonNull(ifAvailable)) {
                ifAvailable.forEach(IPay::pay);
            }
            return "instance.pay()";
        }catch (Exception e){
            return "NoSuchBeanDefinitionException: No qualifying bean of type 'com.appleyk.IPay' available";
        }
    }

    /**主动调用单次jar加载，必传路径*/
//    @RequestMapping(value = "loader",method = RequestMethod.GET)
//    public String loadJar(@RequestParam(name = "jarPath",required = true) String jarPath) throws Exception {
//        try {
//            File file = new File(jarPath);
//            if (file.exists() && file.isFile()){
//                HotClassLoader.loadJar(file.getAbsolutePath(),null);
//                return instance.pay();
//            }
//            throw new IllegalArgumentException("路径非法，无法正确解析！");
//        } catch (FileNotFoundException e) {
//            return "resource does not exist!";
//        }
//    }

//    @RequestMapping(value = "loaders",method = RequestMethod.GET)
//    public String loadAllJars() throws Exception {
//        try {
//            HotClassLoader.loadAllJar();
//            return instance.pay();
//        } catch (FileNotFoundException e) {
//            return "resource does not exist!";
//        }
//    }

    /**固定间隔3秒扫描一次plugins目录，第一次全量加载，其余热替换*/
    @Scheduled(fixedDelay = 3*1000L)
    public void loadJars() throws Exception{
        HotClassLoader.loadAllJar();
        System.out.println("plugins scanned done....");
    }

}
