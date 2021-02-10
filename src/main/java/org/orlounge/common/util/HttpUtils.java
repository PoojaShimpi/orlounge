package org.orlounge.common.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.orlounge.common.AppConstants;
import org.orlounge.exception.ORException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;


public class HttpUtils {

    public static Map<String, String> httpPostJson(String url, String json, Map<String, String> headerMap) {
        HttpClient httpclient = null;
        Map<String, String> mapResponse = new HashMap<String, String>();
        try {
            httpclient = getSSLCertificatedHttpClient();
            HttpPost httpost = new HttpPost(url);
            StringEntity input = new StringEntity(json);
            if (headerMap == null) {
                headerMap = new HashMap<String, String>();
            }
            if (!headerMap.isEmpty()) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    httpost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpost.setEntity(input);
            HttpResponse response = httpclient.execute(httpost);
            mapResponse.put(AppConstants.STATUS_CODE, String.valueOf(response.getStatusLine().getStatusCode()));
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output;
            StringBuffer sbuff = new StringBuffer();
            while ((output = br.readLine()) != null) {
                sbuff.append(output);
            }
            mapResponse.put(AppConstants.RESPONSE, sbuff.toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ORException(e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return mapResponse;
    }

    public static HttpClient getSSLCertificatedHttpClient() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] certificate, String authType) {
                return true;
            }
        };
        SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", 443, sf));
        ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
        DefaultHttpClient httpClient = new DefaultHttpClient(ccm);
        return httpClient;
    }
}
