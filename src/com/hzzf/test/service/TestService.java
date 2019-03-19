package com.hzzf.test.service;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface TestService {
    public String getTestInfo (
            @WebParam(name = "testId", targetNamespace = "http://service.test.hzzf.com/")
                    Integer testId,
            @WebParam(name = "testStr", targetNamespace = "http://service.test.hzzf.com/")
                    String testStr);
}
