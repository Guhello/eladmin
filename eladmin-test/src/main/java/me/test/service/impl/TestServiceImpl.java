package me.test.service.impl;

import lombok.RequiredArgsConstructor;
import me.test.domain.MyTest;
import me.test.repository.TestMapper;
import me.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    @Override
    public List<MyTest> testAll() {
        return testMapper.selectList(null);
    }


}
