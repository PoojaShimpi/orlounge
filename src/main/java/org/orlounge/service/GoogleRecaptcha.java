package org.orlounge.service;

import org.orlounge.common.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Nilay Khandhar on 09/05/19.
 */
public class GoogleRecaptcha {
    private static final Logger logger = Logger.getLogger(GoogleRecaptcha.class.getName());
    private static final String CLIENT_SECRET = "6Le9MqIUAAAAAJUpIGAIC-2OErX6pkluRpKKe3a4";
    private static final String SITE_VERIFY = "https://www.google.com/recaptcha/api/siteverify";

    public static void verifyUserToken(String gRecaptchaResponse) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-type", "application/x-www-form-urlencoded");
        String data = "secret=" + CLIENT_SECRET + "&response=" + gRecaptchaResponse;

        Map<String, String> responseMap = HttpUtils.httpPostJson(SITE_VERIFY, data, headerMap);
        logger.info("response Map  of verify" + responseMap);
    }
}
