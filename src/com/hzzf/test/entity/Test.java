package com.hzzf.test.entity;

public class Test {
    public int testId;
    public String testStr;
    public String testResult;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", testStr='" + testStr + '\'' +
                ", testResult='" + testResult + '\'' +
                '}';
    }
}
