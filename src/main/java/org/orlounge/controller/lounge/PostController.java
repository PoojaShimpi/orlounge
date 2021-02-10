package org.orlounge.controller.lounge;

import com.google.gson.Gson;
import org.orlounge.auth.AuthCheck;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.orlounge.common.AppConstants.GLOBAL_FAILURE_MSG;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
@Controller
@AuthCheck

public class PostController extends BaseController {

    @RequestMapping(value = "/forum/loadMoreForums", produces = "application/json")
    public @ResponseBody
    String loadMoreForums(@RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "limit", required = false) Integer limit,
                          @RequestParam(value = "role", required = false) String role,
                          @RequestParam(value = "type", required = false) String type,
                          HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("application/json");
        int startNum = 0;
        int endNum = 5;
        UserToken token = AppThreadLocal.getTokenLocal();
        if (ProcessData.isValid(start)) {
            startNum = start;
        }
        if (ProcessData.isValid(limit)) {
            endNum = limit;
        }
        if ("all".equalsIgnoreCase(role)) {
            role = "all";
        } else if (ProcessData.isValid(type) && ProcessData.isValid(role)) {

        } else if (ProcessData.isValid(role)) {
            role = (token.isAdmin() || token.getRole().equalsIgnoreCase(AppConstants.ORM_ROLE)) ? role : token.getRole();
        }
        return new Gson().toJson(getBusinessFactory().getForumBusiness().getForumThreads(startNum, endNum, role));
    }

    private String determineForumType(String type) {
        UserToken token = AppThreadLocal.getTokenLocal();
        if (
                "get_well_soon_advisory".equalsIgnoreCase(type) ||
                        "asc_advisory".equalsIgnoreCase(type) ||
                        "hlounge_advisory".equalsIgnoreCase(type) ||
                        "all".equals(type)
        ) {
            return type;
        }
        return token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE) || token.getRole().equalsIgnoreCase(AppConstants.ORM_ROLE) ? type : token.getRole();

    }

    @RequestMapping(value = "/postForumMsg")
    public ModelAndView
    postForumMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "text") String text, @RequestParam(value = "type", required = false) String type) {
        type = determineForumType(type);
        ModelAndView model = new ModelAndView("redirect:loungeworks.html?role=" + type);
        boolean success = false;

        try {
            success = getBusinessFactory().getForumBusiness().savePost(text.trim(), type);
            if (success) {
                MessageUtils.success(request.getSession(), "Forum posted successfully.");
            } else {
                throw new ORException(0);
            }

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/postForumComment")
    public ModelAndView
    postForumComment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "text") String text, @RequestParam(value = "forumId") Integer forumId) {
        UserToken token = AppThreadLocal.getTokenLocal();
        String forumRole = getBusinessFactory().getForumBusiness().getServiceFactory().getForumService().getForum(forumId).getRole();

        String roleViewer;
        if ("all".equalsIgnoreCase(forumRole)) {
            roleViewer = "all";
        } else if (token.isAdmin() || token.getRole().equalsIgnoreCase(AppConstants.ORM_ROLE)) {
            roleViewer = forumRole;
        } else {
            roleViewer = token.getRole();
        }


        ModelAndView model = new ModelAndView("redirect:loungeworks.html?role=" + roleViewer);
        boolean success = false;
        Map map = new HashMap();
        try {
            success = getBusinessFactory().getForumBusiness().saveComment(text.trim(), forumId);
            if (success) {
                MessageUtils.success(request.getSession(), "Comment posted successfully.");
            } else {
                throw new ORException(0);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }

    @RequestMapping(value = "/deleteComment")
    public ModelAndView
    deleteComment(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "commentId") Integer commentId) {
        UserToken token = AppThreadLocal.getTokenLocal();
        String role = token.getRole().equalsIgnoreCase(AppConstants.ADMIN_ROLE) ? AppConstants.ORM_ROLE : token.getRole();

        ModelAndView model = new ModelAndView("redirect:loungeworks.html?role=" + role);
        boolean success;
        try {
            success = getBusinessFactory().getForumBusiness().deleteComment(commentId);
            if (success) {
                MessageUtils.success(request.getSession(), "Comment deleted successfully.");
            } else {
                throw new ORException(0);
            }
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), GLOBAL_FAILURE_MSG);
        }
        return model;

    }
}
