package org.orlounge.controller;

import lombok.extern.slf4j.Slf4j;
import org.orlounge.common.util.ProcessData;
import org.orlounge.exception.ORException;
import org.orlounge.factory.BusinessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Satyam Soni on 9/13/2015.
 */
@Slf4j
public abstract class BaseController {

    public static final String CREATE_LOUNGE = "createLounge";
    public static final String JOIN_LOUNGE = "joinLounge";

    @ExceptionHandler(ORException.class)
    @ResponseBody
    public Map<String, Object> handleSystemException(final ORException exception, final HttpServletResponse response) throws ORException{
        log.error("Error" , exception);
        response.setContentType("application/json");
        return ProcessData.getSuccessMap(false, exception.getMessage());

    }
    @ExceptionHandler(AuthException.class)
    public ModelAndView handleSystemException(final AuthException exception, final HttpServletResponse response) throws ORException{
        log.error("Error" , exception);
        return new ModelAndView("redirect:/");

    }

    @Autowired
    private BusinessFactory businessFactory;

    public BusinessFactory getBusinessFactory() {
        return businessFactory;
    }

    public void setBusinessFactory(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }
}
