package org.orlounge.common.util;

import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Slf4j
public class AmazonSNSService {

    private static AmazonSNS  amazonSNS = null;
    private static final   Random random = new Random();
    private static AmazonSNS getAmazonSNS(String accessKey, String secretKey){
        if(amazonSNS == null){
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
            amazonSNS = AmazonSNSClientBuilder.standard()
                    .withRegion(Regions.US_WEST_2)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .build();
        }
        return amazonSNS;
    }


    public static SmsData generateOtp(String accessKey, String secretKey, String phoneNumber) {
        Integer otpNumber = 100000 + random.nextInt(900000);
        return sendSmsMessage(getAmazonSNS(accessKey, secretKey), phoneNumber, otpNumber);
    }


    private static String e16PhoneNumber(String phoneNumber){
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, "US");
            return phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            log.error("NumberParseException was thrown: " + e);
        }
        return phoneNumber;
    }
    private static SmsData sendSmsMessage(AmazonSNS amazonSNS,
                                       String phoneNumber, Integer otpNumber) {
        String message = otpNumber + " is your ORLounge verification code";
        Map<String, MessageAttributeValue> smsAttributes =
                new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("ORLOUNGE") .withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
        String e16PhoneNumber = e16PhoneNumber(phoneNumber);

        PublishResult publishResult = amazonSNS.publish(new PublishRequest()
                .withMessage(message)
                .withSubject("ORLOUNGE OTP")
                .withMessageAttributes(smsAttributes)
                .withPhoneNumber(e16PhoneNumber));
        log.info("SMS sent for publish id : {} on phone : {}", publishResult.getMessageId(), e16PhoneNumber );
        return SmsData.builder()
                .message(message)
                .otp(""+otpNumber)
                .messageId(publishResult.getMessageId())
                .phoneNumber(e16PhoneNumber)
                .build();

    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    public static class SmsData {
        private String otp;
        private String messageId;
        private String message;
        private String phoneNumber;

    }

}
