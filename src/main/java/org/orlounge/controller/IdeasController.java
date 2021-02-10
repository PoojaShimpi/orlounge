package org.orlounge.controller;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.IdeaBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.util.MessageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@AuthCheck
public class IdeasController extends BaseController {

    @RequestMapping("/ideas.html")
    public ModelAndView index() {
        ModelAndView m = new ModelAndView("jsp/ideas-main");
        m.addObject("num", 20);
        m.addObject("ideas", getBusinessFactory().getVoteBusiness().getIdeasSortByVotes(20));
        return m;
    }

    @RequestMapping("/ideasAll.html")
    public ModelAndView ideasAll() {
        ModelAndView m = new ModelAndView("jsp/ideas-main");
        m.addObject("ideas", getBusinessFactory().getVoteBusiness().getIdeasSortByVotes(-1));
        m.addObject("num", -1);
        return m;
    }


    @RequestMapping("/newidea.html")
    public ModelAndView newidea() {
        ModelAndView m = new ModelAndView("jsp/new-idea-main");
        m.addObject("idea", new IdeaBean());
        return m;
    }

    @RequestMapping(value = "/save-idea.html", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("title") String title, @RequestParam("desc") String desc, @RequestParam("content") String explanation,
                             HttpSession session) {
        IdeaBean ideaBean = new IdeaBean();
        ideaBean.setVotes(0);
        ideaBean.setTitle(title);
        IdeaBean.IdeaContent content = new IdeaBean.IdeaContent();
        content.setDescription(desc);
        content.setContent(explanation);
        ideaBean.setData(content);
        getBusinessFactory().getVoteBusiness().saveIdea(ideaBean);
        ModelAndView m = new ModelAndView("redirect:view-idea.html?idea=" + ideaBean.getIdeaId());
        MessageUtils.success(session, "Your Idea was created Successfully.");
        return m;
    }

    @RequestMapping(value = "/comment-idea.html", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("idea") Integer ideaId, @RequestParam("comment") String comment,
                             HttpSession session) {
        IdeaBean idea = getBusinessFactory().getVoteBusiness().getIdea(ideaId);
        if (idea == null) {
            MessageUtils.failure(session, "Invalid Idea");
            return new ModelAndView("redirect:ideas.html");
        }
        IdeaBean.IdeasComment bean = new IdeaBean.IdeasComment();
        bean.setComment(comment);
        bean.setCommentBy(AppThreadLocal.getTokenLocal().getFirstName() + " " + AppThreadLocal.getTokenLocal().getLastName());
        bean.setUserId(AppThreadLocal.getTokenLocal().getUserId());
        bean.setCommentTime(new Date());

        IdeaBean.IdeaContent content = idea.getData();
        content.getComments().add(bean);
        idea.setData(content);
        getBusinessFactory().getVoteBusiness().saveIdea(idea);

        ModelAndView m = new ModelAndView("redirect:view-idea.html?idea=" + idea.getIdeaId());
        MessageUtils.success(session, "Your Comment has been saved Successfully.");
        return m;
    }

    @RequestMapping(value = "/view-idea.html", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam("idea") Integer ideaId,
                             HttpSession session) {

        final IdeaBean ideaBean = getBusinessFactory().getVoteBusiness().getIdea(ideaId);
        if (ideaBean == null) {
            MessageUtils.failure(session, "Invalid Idea");
            return new ModelAndView("redirect:ideas.html");
        }
        ModelAndView m = new ModelAndView("jsp/view-idea-main");
        m.addObject("idea", ideaBean);
        m.addObject("hadVoted", getBusinessFactory().getVoteBusiness().hadVoted(ideaId));
        return m;
    }

    @RequestMapping(value = "/voteup.html", method = RequestMethod.GET)
    public ModelAndView voteup(@RequestParam("idea") Integer ideaId,
                               HttpSession session) {

        final IdeaBean ideaBean = getBusinessFactory().getVoteBusiness().getIdea(ideaId);
        if (ideaBean == null) {
            MessageUtils.failure(session, "Invalid Idea");
            return new ModelAndView("redirect:ideas.html");
        }
        getBusinessFactory().getVoteBusiness().voteUpIdea(ideaId);
        ModelAndView m = new ModelAndView("redirect:view-idea.html?idea=" + ideaBean.getIdeaId());
        m.addObject("idea", ideaBean);
        return m;
    }

    @RequestMapping(value = "/votedown.html", method = RequestMethod.GET)
    public ModelAndView votedown(@RequestParam("idea") Integer ideaId,
                                 HttpSession session) {

        final IdeaBean ideaBean = getBusinessFactory().getVoteBusiness().getIdea(ideaId);
        if (ideaBean == null) {
            MessageUtils.failure(session, "Invalid Idea");
            return new ModelAndView("redirect:ideas.html");
        }
        getBusinessFactory().getVoteBusiness().voteDownIdea(ideaId);
        ModelAndView m = new ModelAndView("redirect:view-idea.html?idea=" + ideaBean.getIdeaId());
        m.addObject("idea", ideaBean);
        return m;
    }





}
