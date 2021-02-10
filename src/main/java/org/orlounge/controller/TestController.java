package org.orlounge.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.orlounge.bean.PrefListBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Satyam Soni on 6/20/2015.
 */
@Controller
public class TestController {
	
	 @RequestMapping(value = "/saveEditorContent", produces = "application/text")
	    public @ResponseBody
	    String saveEditorContent( HttpServletRequest request, HttpSession httpSession) {
		 System.out.println(request.getParameter("content123"));
	        return "success";
	    }
}
