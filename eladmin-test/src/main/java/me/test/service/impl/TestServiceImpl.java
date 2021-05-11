package me.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import me.test.domain.MyTest;
import me.test.mapper.TestMapper;
import me.test.service.TestService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public IPage<MyTest> testAll() {
        Page<MyTest> page = new Page<>();
        page.setCurrent(1l);
        page.setSize(2l);
        return testMapper.selectPageVo(page);
    }


}
