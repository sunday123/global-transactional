package co.com.example.demo.transaction;
import co.com.example.demo.transaction.mapper.UserInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Description UserInfoServiceImpl
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements IUserInfoService {
}
