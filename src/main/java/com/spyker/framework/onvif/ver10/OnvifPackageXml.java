package com.spyker.framework.onvif.ver10;

import com.spyker.framework.onvif.ver10.entity.WsseUsernameToken;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;

@Slf4j
public class OnvifPackageXml {

    public static String mdeia_get_snap_shot_uri(String username, String password, String token) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/mdeia_get_snap_shot_uri.wsdl");

            String streamXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token);

            return streamXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    private static WsseUsernameToken getWsseUsernameToken(String username, String password) {
        try {
            // nonce
            Random r = new Random();
            String text = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String nonce = "";
            for (int i = 0; i < 32; i++) {
                int index = r.nextInt(text.length());
                nonce = nonce + text.charAt(index);
            }
            // time
            SimpleDateFormat df =
                    new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault());
            String time = df.format(new Date());
            // password
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            //            byte[] b1 = Base64Util.decodeBase64(nonce.getBytes());
            byte[] b1 = Base64.decodeBase64(nonce.getBytes());
            byte[] b2 = time.getBytes();
            byte[] b3 = password.getBytes();
            byte[] b4;
            md.update(b1, 0, b1.length);
            md.update(b2, 0, b2.length);
            md.update(b3, 0, b3.length);
            b4 = md.digest();
            String passwd = new String(Base64.encodeBase64(b4)).trim();
            //
            WsseUsernameToken result = new WsseUsernameToken();
            result.setUsername(username);
            result.setPassword(passwd);
            result.setNonce(nonce);
            result.setCreated(time);
            return result;
        } catch (NoSuchAlgorithmException e) {
            log.error("error-->{}", e);
            return new WsseUsernameToken();
        }
    }

    /** ----------------------------------- 功能 -------------------------------------* */
    @NotNull
    private static StringBuffer getRequestXml(String path) throws IOException {
        Resource resource = new ClassPathResource(path);
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String data = null;
        while ((data = br.readLine()) != null) {
            sb.append(data);
        }
        br.close();
        isr.close();
        is.close();
        return sb;
    }

    /***
     * 获取播放流地址
     * @param username
     * @param password
     * @param token
     * @return
     */
    public static String stream(String username, String password, String token) {
        try {
            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/stream.wsdl");
            String streamXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token);
            return streamXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    /***
     * 云台控制
     * @param username
     * @param password
     * @param token
     * @param leftRight 步长 -1~1 0.1~1 顺时针转动 -0.1~-1逆时针转动 0不转动
     * @param upDown 步长 -1~1 0.1~1 向上看 -0.1~-1 向下看 0不动
     * @param inOut Zoom
     * @return
     */
    public static String ptz(
            String username,
            String password,
            String token,
            Double leftRight,
            Double upDown,
            Double inOut) {
        try {
            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/ptz.wsdl");
            String ptzXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token)
                            .replace("{leftRight}", leftRight.toString())
                            .replace("{upDown}", upDown.toString())
                            .replace("{inOut}", inOut.toString());
            return ptzXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String ptz_goto_preset(
            String username, String password, String token, String presetToken) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/ptz_goto_preset.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token)
                            .replace("{presetToken}", presetToken);
            return result;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String ptz_get_presets(String username, String password, String token) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/ptz_get_presets.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token);
            return result;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String ptz_goto_home(String username, String password, String token) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/ptz_goto_home.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated())
                            .replace("{token}", token);
            return result;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    /** ----------------------------------- 鉴权 -------------------------------------* */
    public static String token(String username, String password) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/token.wsdl");
            String tokenXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated());
            return tokenXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static void doGetSnap(String url, String filePath) {

        //        WsseUsernameToken account = getWsseUsernameToken(userName, password);

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);

            URI uri = builder.build();

            //            String username = "admin"; // 用户名
            //            String password = "grid123456"; // 密码
            //            String userPassword = userName + ":" + password; // 将用户名和密码拼接到一个字符串里中间用 :
            // 分割
            // 通过Base64京userPassword重新编码
            //            String encoding = Base64.encodeBase64String(userPassword.getBytes());
            // 这个设置不能少

            // 创建GET请求
            HttpGet httpGet = new HttpGet(uri);
            //            httpGet.addHeader("Authorization", "Basic " + encoding);
            // 发送请求
            response = httpClient.execute(httpGet);
            // 判断响应状态
            if (response.getStatusLine().getStatusCode() == 200) {
                InputStream result = response.getEntity().getContent();

                File fout = new File(filePath);

                FileUtils.createParentDirectories(fout);

                ImageIO.write(ImageIO.read(result), "jpg", fout);
                //
                //        FileUtils.copyInputStreamToFile(dataInputStream, file);
            }
        } catch (Exception e) {
            log.error("error-->{}", e);
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                log.error("error-->{}", e);
            }
        }
    }

    public static String replay_get_service_capabilities(String username, String password) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/replay_get_service_capabilities.wsdl");

            String streamXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated());

            return streamXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String credential_get_service_capabilities(String username, String password) {
        try {

            WsseUsernameToken account = getWsseUsernameToken(username, password);

            StringBuffer sb = getRequestXml("wsdl/credential_get_service_capabilities.wsdl");

            String streamXml =
                    sb.toString()
                            .replace("{username}", account.getUsername())
                            .replace("{password}", account.getPassword())
                            .replace("{nonce}", account.getNonce())
                            .replace("{created}", account.getCreated());

            return streamXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }
}