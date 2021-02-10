package org.orlounge.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.orlounge.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
public class UserGroupController extends BaseController {


    @RequestMapping(value = "/checkemailbyrole", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    JsonNode checkEmailIdByRoleAndHostipalId(@RequestParam("email") String emailId, @RequestParam("role") String role, @RequestParam("hospital_id") Integer hospitalId) {
        Map result = null;
        try {
            result = getBusinessFactory().getGroupBusiness().checkEmailIdByRoleAndHospitalId(emailId, role, hospitalId);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = new HashMap<>();
            result.put("success", false);
        }
        return new ObjectMapper().convertValue(result, JsonNode.class);
    }
}