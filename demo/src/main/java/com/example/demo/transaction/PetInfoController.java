package com.example.demo.transaction;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description PetInfoController
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@RestController
@RequestMapping("/")
@Slf4j
public class PetInfoController {
    @Autowired
    IPetInfoService petInfoService;

    @PostMapping("/addPet")
    public Integer addPet(@RequestBody PetInfo record) {
        String name =record.getName();
        log.info("name:"+name);
        if (StrUtil.isBlank(name)){
            return null;
        }
        PetInfo p =new PetInfo();
        p.setName(name);
        boolean b=petInfoService.save(p);
        log.info(p.toString()+"save:"+b);
        return b?p.getId():null;
    }
}
