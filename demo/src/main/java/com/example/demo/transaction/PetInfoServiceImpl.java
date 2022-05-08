package com.example.demo.transaction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.transaction.mapper.PetInfoMapper;
import org.springframework.stereotype.Service;

/**
 * Description PetInfoServiceImpl
 * Date 2022/5/8
 * Created by www.ij34.com
 */
@Service
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper,PetInfo> implements IPetInfoService {
}
