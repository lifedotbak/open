package com.spyker.framework.isapi;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Author: jiangxin14 @Date: 2023-11-23 19:49
 */
@Slf4j
public class HttpClientAuthScopeUtils {

    public static CloseableHttpClient httpClient;

    //    private static final int CONNECT_TIMEOUT =
    // common.HttpClientConfig.getHttpConnectTimeout();// 设置连接建立的超时时间为10s
    //    private static final int SOCKET_TIMEOUT = common.HttpClientConfig.getHttpSocketTimeout();
    //    private static final int MAX_CONN = common.HttpClientConfig.getHttpMaxPoolSize(); // 最大连接数
    //    private static final int Max_PRE_ROUTE = common.HttpClientConfig.getHttpMaxPoolSize();
    //    private static final int MAX_ROUTE = common.HttpClientConfig.getHttpMaxPoolSize();

    public static String doDelete(
            String host, short port, String userName, String password, String url, String input) {
        String putUrl = "http://" + host + ":" + port + url;

        log.info("doDelete --> {}", putUrl);

        HttpDelete httpDelete = new HttpDelete(putUrl);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(userName, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpDelete);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();

            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doDelete error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doDelete error:{}", e);
            }
        }
        return response;
    }

    /**
     * PUT操作命令
     *
     * @param url
     * @param input
     * @return
     */
    public static String doPut(
            String host, short port, String userName, String password, String url, String input) {
        String putUrl = "http://" + host + ":" + port + url;

        log.info("doPut --> {}", putUrl);

        HttpPut httpPut = new HttpPut(putUrl);
        httpPut.setEntity(new StringEntity(input, "UTF-8"));
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(userName, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpPut);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();

            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doPut error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doPut error:{}", e);
            }
        }
        return response;
    }

    public static String doPost(
            String host, short port, String userName, String password, String url, String input) {
        String PostUrl = "http://" + host + ":" + port + url;

        HttpPost httpPost = new HttpPost(PostUrl);
        httpPost.setEntity(new StringEntity(input, "UTF-8"));
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(userName, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();
            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doPut error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doPut error:{}", e);
            }
        }
        return response;
    }

    public static String doDelete(
            String host, short port, String userName, String password, String url) {
        String deleteUrl = "http://" + host + ":" + port + url;

        HttpDelete httpDelete = new HttpDelete(deleteUrl);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(userName, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpDelete);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();
            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doPut error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doPut error:{}", e);
            }
        }
        return response;
    }

    public static String doGet(
            String host, short port, String userName, String password, String url) {
        String getUrl = "http://" + host + ":" + port + url;

        log.info("getUrl --> {}", getUrl);

        HttpGet httpget = new HttpGet(getUrl);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(userName, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpget);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();
            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doPut error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doPut error:{}", e);
            }
        }
        return response;
    }

    public static String doPostUploadPhoto(
            String host, short port, String username, String password, String url, String input)
            throws UnsupportedEncodingException {
        String PostUrl = "http://" + host + ":" + port + url;

        HttpPost httpPost = new HttpPost(PostUrl);

        // 创建 MultipartEntityBuilder 实例
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("UserInfoAndRight", input, ContentType.APPLICATION_JSON);
        // 添加图片文件参数
        File file = new File("C:\\Users\\Desktop\\FDLib.jpg");
        builder.addBinaryBody(
                "facePicture", file, ContentType.create("image/jpeg"), file.getName());
        HttpEntity multipart = builder.build();
        // 设置请求体
        httpPost.setEntity(multipart);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port), new UsernamePasswordCredentials(username, password));
        httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        CloseableHttpResponse responseBody = null;
        String response = "";
        try {
            // 由客户端执行(发送)Post请求
            responseBody = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = responseBody.getEntity();
            log.info("响应状态为:" + responseBody.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为:" + responseEntity.getContentLength());
                response = EntityUtils.toString(responseEntity);
                log.info("响应内容为:\n" + response);
            }

        } catch (IOException e) {
            log.error("doPut error:{}", e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (responseBody != null) {
                    responseBody.close();
                }
            } catch (IOException e) {
                log.error("doPut error:{}", e);
            }
        }
        return response;
    }
}