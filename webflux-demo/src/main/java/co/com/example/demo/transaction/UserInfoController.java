package co.com.example.demo.transaction;
import cn.hutool.core.util.StrUtil;
import co.com.example.demo.controllers.TitleOpenFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description UserInfoController
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UserInfoController {
    @Autowired
    IUserInfoService UserInfoService;
    @Autowired
    TitleOpenFeignClient feignClient;

    @PostMapping("/addUser")
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public String addUser(@RequestBody UserAddDto u) {
        log.info(u.toString());
        if (StrUtil.isBlank(u.getP()) || StrUtil.isBlank(u.getU())){
            return "U Pk is null";
        }
        UserInfo pet =new UserInfo();
        pet.setName(u.getP());
        Integer id=feignClient.addPet(pet);
        if (id==null){
            return "SAVE PET FAIL";
        }
        UserInfo p =new UserInfo();
        p.setName(u.getU());
        p.setPetId(id);
//        p.setId(1); //回滚上一个服务
        boolean b=UserInfoService.save(p);
        log.info(p.toString()+"save:"+b);
        return ""+b;
    }
}
