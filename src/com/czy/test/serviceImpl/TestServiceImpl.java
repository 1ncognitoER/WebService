package com.czy.test.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.czy.test.dao.TestDao;
import com.czy.test.entity.Test;
import com.czy.test.service.TestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "com.czy.test.service.TestService",
        serviceName = "testService",
        targetNamespace = "http://service.test.czy.com/")
public class TestServiceImpl implements TestService {

    // 日志
    private final static Log log = LogFactory.getLog(TestServiceImpl.class);

    @Autowired
    private TestDao testDao;

    @Override
    public String getTestInfo(Integer testId, String testStr) {

        Map map = new HashMap();
        map.put("testId", testId);
        map.put("testStr", testStr);
        Test test = testDao.getTestInfo(map);

        log.info("test: " + test.toString());

        String resultStr = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            resultStr = mapper.writeValueAsString(test);
        } catch (JsonProcessingException e) {
            log.error("数据格式转换错误！");
        }
        return resultStr;
    }

}
