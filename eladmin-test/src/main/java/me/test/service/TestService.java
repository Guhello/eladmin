package me.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import me.test.domain.MyTest;

import java.util.List;

public interface TestService {

    IPage<MyTest> testAll();

}
