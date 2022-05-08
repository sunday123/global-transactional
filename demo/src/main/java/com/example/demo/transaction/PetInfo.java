package com.example.demo.transaction;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/**
 * Description PetInfo
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@Data
public class PetInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
}
