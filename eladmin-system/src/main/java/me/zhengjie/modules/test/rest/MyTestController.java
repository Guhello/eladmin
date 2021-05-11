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
package me.zhengjie.modules.test.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.test.domain.MyTest;
import me.zhengjie.modules.test.service.MyTestService;
import me.zhengjie.modules.test.service.dto.MyTestQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://el-admin.vip
* @author test
* @date 2021-05-11
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "测试生成管理")
@RequestMapping("/api/myTest")
public class MyTestController {

    private final MyTestService myTestService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('myTest:list')")
    public void download(HttpServletResponse response, MyTestQueryCriteria criteria) throws IOException {
        myTestService.download(myTestService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询测试生成")
    @ApiOperation("查询测试生成")
    @PreAuthorize("@el.check('myTest:list')")
    public ResponseEntity<Object> query(MyTestQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(myTestService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增测试生成")
    @ApiOperation("新增测试生成")
    @PreAuthorize("@el.check('myTest:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody MyTest resources){
        return new ResponseEntity<>(myTestService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改测试生成")
    @ApiOperation("修改测试生成")
    @PreAuthorize("@el.check('myTest:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody MyTest resources){
        myTestService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除测试生成")
    @ApiOperation("删除测试生成")
    @PreAuthorize("@el.check('myTest:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        myTestService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}