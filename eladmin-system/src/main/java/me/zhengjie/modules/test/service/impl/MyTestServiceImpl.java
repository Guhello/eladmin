/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.test.service.impl;

import me.zhengjie.modules.test.domain.MyTest;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.test.repository.MyTestRepository;
import me.zhengjie.modules.test.service.MyTestService;
import me.zhengjie.modules.test.service.dto.MyTestDto;
import me.zhengjie.modules.test.service.dto.MyTestQueryCriteria;
import me.zhengjie.modules.test.service.mapstruct.MyTestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://el-admin.vip
* @description 服务实现
* @author test
* @date 2021-05-11
**/
@Service
@RequiredArgsConstructor
public class MyTestServiceImpl implements MyTestService {

    private final MyTestRepository myTestRepository;
    private final MyTestMapper myTestMapper;

    @Override
    public Map<String,Object> queryAll(MyTestQueryCriteria criteria, Pageable pageable){
        Page<MyTest> page = myTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(myTestMapper::toDto));
    }

    @Override
    public List<MyTestDto> queryAll(MyTestQueryCriteria criteria){
        return myTestMapper.toDto(myTestRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public MyTestDto findById(Integer id) {
        MyTest myTest = myTestRepository.findById(id).orElseGet(MyTest::new);
        ValidationUtil.isNull(myTest.getId(),"MyTest","id",id);
        return myTestMapper.toDto(myTest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MyTestDto create(MyTest resources) {
        return myTestMapper.toDto(myTestRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MyTest resources) {
        MyTest myTest = myTestRepository.findById(resources.getId()).orElseGet(MyTest::new);
        ValidationUtil.isNull( myTest.getId(),"MyTest","id",resources.getId());
        myTest.copy(resources);
        myTestRepository.save(myTest);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            myTestRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<MyTestDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (MyTestDto myTest : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" name",  myTest.getName());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}