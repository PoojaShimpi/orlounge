package org.orlounge.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.orlounge.bean.TmpOtp;
import org.orlounge.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class TmpOtpController extends BaseController {


    @RequestMapping(value = "/requestotp", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    JsonNode generateOtpForUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "hospitalPhNo", required = true) String hospitalPhNo) {
        Map result = new HashMap<>();

        TmpOtp tmpOtp = new TmpOtp();
        tmpOtp.setEmail(email);
        tmpOtp.setHospitalTelPh(hospitalPhNo);
        tmpOtp.setCreateTs(new Date());

        result = getBusinessFactory().getTmpOtpBusiness().saveOrUpdateTmpOtp(tmpOtp);

        return new ObjectMapper().convertValue(result, JsonNode.class);
    }

    @RequestMapping(value = "/verfiyotp", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    JsonNode verfiyOtpForUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "otp", required = true) Integer optNumber) {
        Map result = new HashMap<>();
        result = getBusinessFactory().getTmpOtpBusiness().verifyOtpForUser(email, optNumber);
        return new ObjectMapper().convertValue(result, JsonNode.class);
    }
}