package org.orlounge.controller.lounge;

import com.google.gson.Gson;
import org.orlounge.auth.AuthCheck;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.orlounge.common.AppConstants.GLOBAL_FAILURE_MSG;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Controller
@AuthCheck
public class MessageBoardController extends BaseController {


    @RequestMapping("/messageboard.html")
    public ModelAndView messageboard(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) throws AuthException {
        if ("Advisory Lounge".equalsIgnoreCase(AppThreadLocal.getTokenLocal().getCurrentGroupName())) {
            return new ModelAndView("redirect:loungeworks.html?role=get_well_soon_advisory");
        } else if (AppThreadLocal.getTokenLocal().getCurrentGroupId() == null && AppThreadLocal.getTokenLocal().isAdmin()) {
            return new ModelAndView("redirect:orltoenter.html");
        } else {
            ModelAndView model = new ModelAndView("jsp/message-board-main");
            if (page == null || page == 0) {
                //model
            } else {

            }
            return model;
        }

    }





    @RequestMapping(value = "/msg/loadMoreMsgs", produces = "application/json")
    public @ResponseBody
    String loadMoreForums(@RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "limit", required = false) Integer limit,
                          HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("application/json");
        int startNum = 0;
        int endNum = 5;
        if (ProcessData.isValid(start)) {
            startNum = start;
        }
        if (ProcessData.isValid(limit)) {
            endNum = limit;
        }
        return new Gson().toJson(getBusinessFactory().getMessageBoardBusiness().getMessageThreads(startNum, endNum));
    }

    @RequestMapping(value = "/postMsg")
    public ModelAndView
    postForumMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "text") String text) {
        ModelAndView model = new ModelAndView("redirect:messageboard.html");
        boolean success = false;
        try {
            success = getBusinessFactory().getMessageBoardBusiness().saveMsg(text.trim());
            if (success) {
                MessageUtils.success(request.getSession(), "Message Posted successfully.");
            } else {
                throw new ORException(0);
            }


        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/postMsgComment")
    public ModelAndView
    postForumComment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "text") String text, @RequestParam(value = "msgId") Integer forumId) {
        ModelAndView model = new ModelAndView("redirect:messageboard.html");
        boolean success = false;
        Map map = new HashMap();
        try {
            success = getBusinessFactory().getMessageBoardBusiness().saveComment(text.trim(), forumId);
            if (success) {
                MessageUtils.success(request.getSession(), "Comment Saved successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/deleteMsgComment")
    public ModelAndView
    deleteComment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "commentId") Integer commentId) {
        ModelAndView model = new ModelAndView("redirect:messageboard.html");
        boolean success = false;
        Map map = new HashMap();
        try {
            success = getBusinessFactory().getMessageBoardBusiness().deleteComment(commentId);
            if (success) {
                MessageUtils.success(request.getSession(), "Comment Deleted successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }
}
