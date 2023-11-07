package test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

public class EzvizDDNSAPI {
    private String access_token;
    private String areaDomain;
    private String appKey;
    private String appSecret;

    public EzvizDDNSAPI()
    {
       
    }
    
    public EzvizDDNSAPI(String AppKey,String AppSecret)
    {
    	appKey = AppKey;
    	appSecret = AppSecret;
    }
    
    public String getKey() {
    	return appKey;
    }
    
    public String getSecret() {
    	return appSecret;
    }

    public void setAccess_token(String token) {
    	access_token = token;
    }
    
    public void setAreaDomain(String domain) {
    	areaDomain = domain;
    }
    
    public String getAccess_token() {
        return access_token;
    }

    public String getAreaDomain() {
        return areaDomain;
    }

    /*Provide a neutral login web page: https://openauth.ezvizlife.com/oauth/ddns/{appKey}?areaId={areaId},
                 and use the browser to open the web page.*/
    public void Get_access_token(String appKey,int areaId)
    {

    }

    public void Get_access_token(String appKey,String appSecret)
    {
        try {
            String url = "https://open.ezvizlife.com/api/lapp/token/get";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","open.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "appKey=" + appKey + "&appSecret=" + appSecret;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            JSONObject DetailInfo = RetValue.getJSONObject("data");
            access_token = DetailInfo.getString("accessToken");
            areaDomain = DetailInfo.getString("areaDomain");
            System.out.println(access_token);
            System.out.println(areaDomain);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*This API is used to get the device DDNS information, including the shared device DDNS information.*/
    public void Get_device_DDNS_information(String accessToken,String deviceSerial,String domain)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/get";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&deviceSerial=" + deviceSerial;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            JSONObject DetailInfo = RetValue.getJSONObject("data");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*This API is used to set the device DDNS domain, including the shared device's DDNS domain.*/
    public void Set_device_DDNS_domain(String accessToken,String deviceSerial,String domain)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/setDomain";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&deviceSerial="+ deviceSerial +"&domain="+domain;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
            //JSONObject DetailInfo = RetValue.getJSONObject("data");
            //System.out.println(DetailInfo.getString("domain"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Set device DDNS auto mapping mode by single device.*/
    public void Set_device_DDNS_auto_mapping(String accessToken,String deviceSerial)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/mode/setAutomatic";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&deviceSerial=" + deviceSerial;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
            //JSONObject DetailInfo = RetValue.getJSONObject("data");
            //System.out.println(DetailInfo.getString("domain"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Set device DDNS manual mapping mode by single device.*/
    public void Set_device_DDNS_manual_mapping(String accessToken,String deviceSerial,int cmdPort,int httpPort)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/mode/setManual";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + access_token + "&deviceSerial=" + deviceSerial + "&cmdPort="+Integer.toString(cmdPort)+"&httpPort="+Integer.toString(httpPort);
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Turn page (the maximum device number on each page is 50) to get the DDNS information of all the devices
    (including shared devices) in the current account.*/
    public JSONObject Get_DDNS_information_of_all_the_devices_in_the_account(String accessToken)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/list";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&pageSize=10&pageStart=0";
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            return RetValue;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*Share DDNS information to other account by single device.*/
    public void Share_device_DDNS_information(String accessToken,String deviceSerial,String account)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/share";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&deviceSerial=" + deviceSerial + "&account=" + account;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
            //JSONObject DetailInfo = RetValue.getJSONObject("data");
            //System.out.println(DetailInfo.getString("domain"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Add device to this account.*/
    public void Add_Device(String accessToken,String deviceSerial,String validateCode)
    {
        try {
            String url = areaDomain + "/api/lapp/device/add";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + accessToken + "&deviceSerial=" + deviceSerial + "&validateCode="+validateCode;
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
            //JSONObject DetailInfo = RetValue.getJSONObject("data");
            //System.out.println(DetailInfo.getString("domain"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Turn page (the maximum device number on each page is 50) to get the DDNS information of all the shared devices in the current account.*/
    public void Get_DDNS_information_of_all_the_shared_devices_in_the_account(String accessToken,int pageSize,int pageStart)
    {
        try {
            String url = areaDomain + "/api/lapp/ddns/share/list";
            URL getDeviceUrl = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)getDeviceUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            String sendParam = "accessToken=" + access_token + "&pageSize=" + new Integer(pageSize).toString() + "&pageStart=" + new Integer(pageStart).toString();
            System.out.println(sendParam);
            PostParam.print(sendParam);
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            int RetCode = Integer.parseInt(RetValue.getString("code"));
            String Message = RetValue.getString("msg");
            System.out.println("RetCode:"+RetCode+"\nRetMsg:"+Message);
            //JSONObject DetailInfo = RetValue.getJSONObject("data");
            //System.out.println(DetailInfo.getString("domain"));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Get area list of all the nations.*/
    public JSONObject Get_area_list_of_all_the_nations()
    {
        try {
            String url = "https://open.ezvizlife.com/api/lapp/area/list";
            URL urlAreaList = new URL(url);
            /*Set Http Request Header*/
            HttpURLConnection connection = (HttpURLConnection)urlAreaList.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Host","isgpopen.ezvizlife.com");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            PrintWriter PostParam = new PrintWriter(connection.getOutputStream());
            PostParam.flush();

            BufferedReader inBuf = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JSONObject RetValue = new JSONObject(new String(inBuf.readLine().getBytes(),"utf-8"));
            return RetValue;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

