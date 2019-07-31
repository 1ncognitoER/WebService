package com.czy.util;

/*import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;*/

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestMethodByAxis {
    /**
     * @Author Caozy
     * @Date 2018-11-12 15:23
     * @Method main
     * @Param [args]
     * @Return void
     * @Declare 接口调用测试方法 axis
     */
    public static void main (String[] args) {
        // 指出service所在完整的URL,http://ip:端口号/项目名/webservice/sei(即webservice接口名)?wsdl
        String endpoint = "http://localhost:9099/service/testService?wsdl";
        // 调用接口的targetNamespace,webservice接口所在的包名，逆序，一直到src下
        String targetNamespace = "http://service.test.czy.com/";
        // 所调用接口的方法method
        String method = "getTestInfo";
        // 创建一个服务(service)调用(call)
        Service service = new Service();
        Call call = null;
        try {
            call = (Call) service.createCall();// 通过service创建call对象
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            URL url = new URL(endpoint);
            call.setTargetEndpointAddress(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //设定超时时限
        //call.setTimeout(new Integer(60000));

        //命名空间(wsdl文件中的targetNameSpace属性值) 以及方法名
        QName qName = new QName(targetNamespace, method);
        call.setOperationName(qName);
        //设置参数格式
        call.addParameter("testId", XMLType.XSD_INTEGER, ParameterMode.IN);
        call.addParameter("testStr", XMLType.XSD_STRING, ParameterMode.IN);

        //设置返回值类型
        call.setReturnType(XMLType.XSD_STRING);
        //模拟参数
        Integer testId = 0;
        String testStr = "Service Test";
        //执行接口
        String returnMsg = null;
        try {
            returnMsg = (String) call.invoke(new Object[] {testId, testStr});
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //将json字符串转换为JSON对象
        //Object object =  JSONObject.fromObject(returnMsg);
        //Object object =  JSONArray.fromObject(returnMsg);
        System.out.println(returnMsg);
    }

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
            //int testId = 999;
            String testId = "999";
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
