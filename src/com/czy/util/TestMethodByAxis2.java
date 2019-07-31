package com.czy.util;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class TestMethodByAxis2 {
    /**
     * @Author Caozy
     * @Date 2019-03-15 17:27
     * @Method main
     * @Param [args]
     * @Return void
     * @Declare 接口调用测试方法 document方式
     */
    public static void main (String[] args) {
        try {
            // 创建服务地址WebService的URL,注意不是WSDL的URL
            String url = "http://localhost:9090/service/testService?wsdl";
            // 调用接口的targetNamespace,webservice接口所在的包名，逆序，一直到src下
            String targetNamespace = "http://service.test.czy.com/";
            // 所调用接口的方法method
            String methodName = "getTestInfo";

            Options options = new Options();
            // 指定WebService的URL
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);

            ServiceClient serviceClient = new ServiceClient();
            serviceClient.setOptions(options);

            OMFactory fac = OMAbstractFactory.getOMFactory();
            // 指定接口命名空间，第二个参数（perfix）可以不用填
            OMNamespace omNs = fac.createOMNamespace(targetNamespace, "");
            // 指定接口方法
            OMElement method = fac.createOMElement(methodName, omNs);
            // 指定接口方法的参数
            Integer paramTestId = new Integer(999);
            String paramTestStr = "Service Test";

            OMElement value = fac.createOMElement("testId", omNs);
            value.addChild(fac.createOMText(value, paramTestId.toString()));
            method.addChild(value);

            value = fac.createOMElement("testStr", omNs);
            //value.setText(paramTestStr);
            value.addChild(fac.createOMText(value, paramTestStr));
            method.addChild(value);

            method.build();

            //远程调用web服务
            OMElement result = serviceClient.sendReceive(method);
            System.out.println(result);

        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }

    /*
     * @Author Caozy
     * @Date 2019-03-15 17:28
     * @Method main
     * @Param [args]
     * @Return void
     * @Declare RPC调用方式
     */
    /*public static void main (String[] args) {
        try {
            // 指出service所在完整的URL,http://ip:端口号/项目名/webservice/sei(即webservice接口名)?wsdl
            String endpoint = "http://localhost:9099/service/testService?wsdl";
            // 调用接口的targetNamespace,webservice接口所在的包名，逆序，一直到src下
            String targetNamespace = "http://service.test.czy.com/";
            // 所调用接口的方法method
            String method = "getTestInfo";

            RPCServiceClient client = new RPCServiceClient();
            Options options = client.getOptions();
            options.setTo(new EndpointReference(endpoint));
            options.setTimeOutInMilliSeconds(1000 * 60 * 5);// 单位毫秒
            options.setAction(method);

            QName qName = new QName(targetNamespace, method);
            // 参数
            Integer testId = 999;
            String testStr = "web service test";
            Object[] params = new Object[] {testId, testStr};
            Object[] response = client.invokeBlocking(qName, params, new Class[]{String.class});
            String results = (String) response[0];
            System.out.println(results);

        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        }
    }*/
}
