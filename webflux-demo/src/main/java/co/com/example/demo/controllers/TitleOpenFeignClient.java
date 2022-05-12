package co.com.example.demo.controllers;

import co.com.example.demo.transaction.UserInfo;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Description TitleOpenFeignClient
 * Date 2021/12/18
 * Created by www.ij34.com
 */
@FeignClient(value = "nacos-provider",path = "/")
public interface TitleOpenFeignClient {
    @PostMapping("/download")
    Response downloadExcel(String name);

    @PostMapping("/addPet")
    public Integer addPet(@RequestBody UserInfo record);
}
