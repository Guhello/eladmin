package me.zhengjie.modules.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.test.service.TestService;
import me.zhengjie.annotation.AnonymousAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "APP：测试")
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class MyController {

    private final TestService testService;

    @ApiOperation("测试数据")
    @AnonymousAccess
    @GetMapping(value = "/testData")
    public ResponseEntity<Object> testData() {
        return new ResponseEntity(testService.testAll(), HttpStatus.OK);
    }

}
