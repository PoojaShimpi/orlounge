package org.orlounge.business;

import org.orlounge.bean.TmpOtp;
import org.orlounge.common.AppConstants;
import org.orlounge.common.util.AmazonSNSService;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.Validation;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Nilay on 19/9/18.
 */
@Component
public class TmpOtpBusiness extends AbstractBaseBusiness {


    public Map saveOrUpdateTmpOtp(TmpOtp requestTmpOtp) {
        Map result = new HashMap<>();
        String message = "";
        boolean success = false;
        try {
            boolean valid = validateTmpOtp(requestTmpOtp);
            if (!valid) {
                success = false;
                message = "Invalid Details";
                result.put("success", success);
                result.put("message", message);
                return result;
            } else {
                String email = requestTmpOtp.getEmail();
                TmpOtp dbTmpOtp = getServiceFactory().getLoginService().getTmpOtpByEmail(email);
                if (dbTmpOtp != null) {
                    Long tmpOtpId = dbTmpOtp.getTmpOtpId();
                    requestTmpOtp.setTmpOtpId(tmpOtpId);
                }
                String phone = requestTmpOtp.getHospitalTelPh();
                String ACCESS_KEY = getConfigMap().get(AppConstants.SNS_ACCESS_KEY_PROP);
                String SECRET_KEY = getConfigMap().get(AppConstants.SNS_SECRET_KEY_PROP);
                AmazonSNSService.SmsData map = AmazonSNSService.generateOtp(ACCESS_KEY, SECRET_KEY, phone);
                Integer otpNumber = Integer.valueOf(map.getOtp());
                String messageId = map.getMessageId();
                requestTmpOtp.setOtpNumber(otpNumber);
                requestTmpOtp.setMessgae_id(messageId);

                getServiceFactory().getLoginService().saveOrUpdate(requestTmpOtp);
                result.put("success", true);
                return result;
            }
        } catch (Exception e) {
            throw new ORException(e, 3);
        }
    }

    private boolean validateTmpOtp(TmpOtp requestTmpOtp) {
        try {
            return ProcessData.isValid(requestTmpOtp)
                    && ProcessData.isValid(requestTmpOtp.getEmail())
                    && Validation.validateEmail(requestTmpOtp.getEmail())
                    && ProcessData.isValid(requestTmpOtp.getHospitalTelPh());
        } catch (Exception e) {
            return false;
        }
    }

    public Map verifyOtpForUser(String email, Integer otpNumber) {
        Map result = new HashMap<>();
        String message = "";
        boolean success = false;
        try {
            boolean valid = validateForOtp(email, otpNumber);
            if (!valid) {
                success = false;
                message = "Invalid Details";
                result.put("success", success);
                result.put("message", message);
                return result;
            } else {
                TmpOtp dbTmpOtp = getServiceFactory().getLoginService().getTmpOtpByEmail(email);
                if (dbTmpOtp != null) {
                    Integer dbOtpNumber = dbTmpOtp.getOtpNumber();
                    if (dbOtpNumber.equals(otpNumber)) {
                        success = true;
                        message = "Valid OTP";
                        result.put("success", success);
                        result.put("message", message);
                        getServiceFactory().getLoginService().deleteTmpById(dbTmpOtp);
                    } else {
                        invalidOtpResult(result);
                    }
                } else {
                    invalidOtpResult(result);
                }
                return result;
            }
        } catch (Exception e) {
            throw new ORException(e, 3);
        }
    }

    private void invalidOtpResult(Map result) {
        boolean success;
        String message;
        success = false;
        message = "Invalid OTP";
        result.put("success", success);
        result.put("message", message);
    }

    private boolean validateForOtp(String email, Integer otpNumber) {
        try {
            return ProcessData.isValid(email)
                    && Validation.validateEmail(email)
                    && ProcessData.isValid(otpNumber);
        } catch (Exception e) {
            return false;
        }
    }

}
