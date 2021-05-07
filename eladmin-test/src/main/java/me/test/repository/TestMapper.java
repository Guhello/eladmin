package me.test.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.test.domain.MyTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<MyTest> {

    List<MyTest> selectTest();


}
