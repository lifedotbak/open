package com.spyker.framework.axis;

import lombok.extern.slf4j.Slf4j;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import java.io.FileInputStream;

@Slf4j
public class WebServiceClientUtils {

    public static final String uploadCommonData(
            String myUsername, String myPassword, byte[] Buffer) {

        String result = "";

        try {

            // 获取域名地址，server定义的
            String soapaction = "http://tempuri.org/";

            // 不带？wsdl后缀
            String endpoint = "http://114.215.220.214:8018/PlatfromService.asmx";
            // 创建一个服务(service)调用(call)
            Service service = new Service();
            // 通过service创建call对象
            Call call = (Call) service.createCall();
            // 设置service所在URL
            call.setTargetEndpointAddress(endpoint);
            // qqCheckOnline 是net 那边的方法 "http://WebXml.com.cn/" 这个也要注意Namespace 的地址,不带也会报错
            call.setOperationName(new QName(soapaction, "uploadCommonData"));
            // qqCode也是.NET那边方法的参数名，即qqCheckOnline的参数名
            call.addParameter(
                    new QName(soapaction, "myUsername"),
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(
                    new QName(soapaction, "myPassword"),
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);

            call.addParameter(
                    new QName(soapaction, "Buffer"),
                    org.apache.axis.encoding.XMLType.SOAP_BASE64BINARY,
                    javax.xml.rpc.ParameterMode.IN);

            // 避免Java调用.net的webService产生“服务器未能识别 HTTP 标头 SOAPAction 的值”错误
            call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回参数的类型
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapaction + "uploadCommonData"); // 这个也要注意

            // Object 数组封装了参数
            String ret = (String) call.invoke(new Object[] {myUsername, myPassword, Buffer});
            System.out.println("--------" + ret);

            result = ret;

        } catch (Exception e) {
            log.error("uploadCommonData error -> {}", e);
        }

        return result;
    }

    /**
     * https://www.cnblogs.com/zhoulian/p/9323908.html
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        try {

            // 获取域名地址，server定义的
            String soapaction = "http://tempuri.org/";

            // 不带？wsdl后缀
            String endpoint = "http://114.215.220.214:8018/PlatfromService.asmx";
            // 创建一个服务(service)调用(call)
            Service service = new Service();
            // 通过service创建call对象
            Call call = (Call) service.createCall();
            // 设置service所在URL
            call.setTargetEndpointAddress(endpoint);
            // qqCheckOnline 是net 那边的方法 "http://WebXml.com.cn/" 这个也要注意Namespace 的地址,不带也会报错
            call.setOperationName(new QName(soapaction, "uploadCommonData"));
            // qqCode也是.NET那边方法的参数名，即qqCheckOnline的参数名
            call.addParameter(
                    new QName(soapaction, "myUsername"),
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);
            call.addParameter(
                    new QName(soapaction, "myPassword"),
                    org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);

            call.addParameter(
                    new QName(soapaction, "Buffer"),
                    org.apache.axis.encoding.XMLType.SOAP_BASE64BINARY,
                    javax.xml.rpc.ParameterMode.IN);

            // 避免Java调用.net的webService产生“服务器未能识别 HTTP 标头 SOAPAction 的值”错误

            call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回参数的类型
            call.setUseSOAPAction(true);
            call.setSOAPActionURI(soapaction + "uploadCommonData"); // 这个也要注意

            FileInputStream input = new FileInputStream("D:\\cxf\\PlatfromService.xml");
            byte[] image = new byte[input.available()];

            // Object 数组封装了参数
            String ret = (String) call.invoke(new Object[] {"aaaaa", "vvvvv", image});
            System.out.println("--------" + ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}