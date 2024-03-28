package com.spyker.framework.onvif;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Slf4j
public class PackageXml {

    public static String getSnaoShotUri(String username, String password, String token) {
        try {
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/get_snapshoturi.wsdl");
            String streamXml =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
                            .replace("{token}", token);
            return streamXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    /** ----------------------------------- 功能 -------------------------------------* */
    /***
     * 获取播放流地址
     * @param username
     * @param password
     * @param token
     * @return
     */
    public static String streamUri(String username, String password, String token) {
        try {
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/stream.wsdl");
            String streamXml =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
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
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/ptz.wsdl");
            String ptzXml =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
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

    public static String gotoPreset(
            String username, String password, String token, String presetName) {
        try {
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/goto_preset.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
                            .replace("{token}", token)
                            .replace("{indexPresetName}", presetName);
            return result;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String getPresets(String username, String password, String token) {
        try {
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/get_presets.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
                            .replace("{token}", token);
            return result;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    public static String gotoHome(String username, String password, String token) {
        try {
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/goto_home.wsdl");
            String result =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"))
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
            Map<String, String> account = getWsseUsernameToken(username, password);
            StringBuffer sb = getRequestXml("wsdl/token.wsdl");
            String tokenXml =
                    sb.toString()
                            .replace("{username}", account.get("username"))
                            .replace("{password}", account.get("password"))
                            .replace("{nonce}", account.get("nonce"))
                            .replace("{created}", account.get("created"));
            return tokenXml;
        } catch (IOException e) {

            log.error("error-->{}", e);

            return null;
        }
    }

    private static Map<String, String> getWsseUsernameToken(String username, String password) {
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
            Map<String, String> result = new HashMap<String, String>();
            result.put("username", username);
            result.put("password", passwd);
            result.put("nonce", nonce);
            result.put("created", time);
            return result;
        } catch (NoSuchAlgorithmException e) {
            log.error("error-->{}", e);
            return null;
        }
    }

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

    private String replaceBaseInfo(
            String xml,
            String username,
            String password,
            String token,
            String nonce,
            String created) {

        String result =
                xml.replace("{username}", username)
                        .replace("{password}", password)
                        .replace("{nonce}", nonce)
                        .replace("{created}", created)
                        .replace("{token}", token);

        return result;
    }
}