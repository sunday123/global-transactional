package co.com.example.demo.transaction;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description UserInfo
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@Data
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer petId;
}
