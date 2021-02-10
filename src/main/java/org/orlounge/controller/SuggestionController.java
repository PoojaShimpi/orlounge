package org.orlounge.controller;

import io.netty.util.internal.StringUtil;
import org.orlounge.bean.VotingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 7/3/2016.
 */
@Controller
public class SuggestionController extends BaseController {

    @RequestMapping(value = "/sendSuggestion", produces = "application/json")
    public @ResponseBody
    String sendMail(@RequestBody String text) {
        try {
            List<String> toNotify = Arrays.asList("support@orlounge.com");
            getBusinessFactory().getMailBusiness().sendMail(null, StringUtil.join(",",toNotify).toString(), null, "Suggestion", text, null);
            return "";
        } catch (Exception ex) {
            // throw new ORException(2);
            return "";
        }

    }

    @RequestMapping(value = "/sendSuggestionVote", produces = "application/json")
    public @ResponseBody
    String sendMailVote(@RequestParam String text, @RequestParam Integer priority) {
        try {
            List<String> toNotify = Arrays.asList("support@orlounge.com");
            String toPass = " A member has suggested ( Priority" + priority + " ) : " + text;
            getBusinessFactory().getMailBusiness().sendMail(null,  StringUtil.join(",",toNotify).toString(), null, "Suggestion|Prority:" + priority, toPass, null);
            return "";
        } catch (Exception ex) {
            // throw new ORException(2);
            return "";
        }

    }

    @RequestMapping(value = "/sendVotes", produces = "application/json")
    public @ResponseBody
    String saveVote(@RequestBody List<VoteResult> results) {
        try {
            for (VoteResult v : results) {
                VotingBean bean = getBusinessFactory().getVoteBusiness().getVotingBeanById(v.getVoteId());
                int curr = bean.getVoteNumber();
                int latest = curr + v.getPriority();
                bean.setVoteNumber(latest);
                getBusinessFactory().getVoteBusiness().saveVoting(bean);
            }
            return "";
        } catch (Exception ex) {
            // throw new ORException(2);
            return "";
        }

    }

    @RequestMapping(value = "/reportBug", produces = "application/json")
    public @ResponseBody
    String reportBug(@RequestBody ReportBugReq bug) {
        try {
            List<String> toNotify = Arrays.asList("bug@orlounge.com");
            getBusinessFactory().getMailBusiness().sendMail(null,  StringUtil.join(",",toNotify).toString(), null, "Bug in " + bug.getType(), bug.getText(), null);
            return "";
        } catch (Exception ex) {
            // throw new ORException(2);
            return "";
        }

    }

    private static class ReportBugReq {
        private String type;
        private String text;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }



    public static class VoteResult {
        private Integer voteId;
        private Integer priority;

        public Integer getVoteId() {
            return voteId;
        }

        public void setVoteId(Integer voteId) {
            this.voteId = voteId;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }
    }
}
