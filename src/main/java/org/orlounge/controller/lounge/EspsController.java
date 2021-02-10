package org.orlounge.controller.lounge;

import org.joda.time.DateTimeComparator;
import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.EspsBillInfo;
import org.orlounge.bean.GroupBean;
import org.orlounge.business.RegisterationEmailer;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.BillingStatus;
import org.orlounge.common.UserInfo;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@AuthCheck

public class EspsController extends BaseController {


    @RequestMapping(value = "/esps.html", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView m = new ModelAndView("jsp/buy-esps");
        EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(AppThreadLocal.getTokenLocal().getCurrentGroupId());

        m.addObject("info", info == null ? new EspsBillInfo() : info);
        return m;
    }


    @RequestMapping(value = "/view-esps.html", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam("groupId") Integer groupId) {
        ModelAndView m = new ModelAndView("jsp/buy-esps");
        EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(groupId);
        m.addObject("info", info);
        return m;
    }

    @RequestMapping(value = "/send-reminder-email.html", method = RequestMethod.GET)
    public ModelAndView sendReminder(@RequestParam("groupId") Integer groupId, HttpSession session) {
        ModelAndView m = new ModelAndView("redirect:manage-esps.html");

        try {
            EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(groupId);
            GroupBean groupBean = getBusinessFactory().getGroupBusiness().getGroup(groupId);
            List<UserInfo> users = getBusinessFactory().getUserService().getLsaUserForGroup(groupId);
            List<String> tos = new ArrayList<>();
            tos.add(info.getBillMail());
            for (UserInfo i : users) {
                tos.add(i.getEmail());
            }

            final String mailText = new RegisterationEmailer()
                    .espsReminder(groupBean.getGroupName(), info);

            getBusinessFactory().getMailBusiness().sendMail(StringUtils.collectionToCommaDelimitedString(tos), null, null, "ESPS Expiry Reminder", mailText, null);
            MessageUtils.success(session, "Reminder Mail Sent successfully.");
        } catch (Exception e) {
            MessageUtils.failure(session, "Error sending mail. Please try again later.");
            throw new ORException(e);
        }
        return m;
    }


    @RequestMapping(value = "/mark-expired.html", method = RequestMethod.GET)
    public ModelAndView markExpired(@RequestParam("groupId") Integer groupId, HttpSession session) {
        ModelAndView m = new ModelAndView("redirect:manage-esps.html");

        try {
            EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(groupId);
            info.setStatus(BillingStatus.EXPIRED.name());
            getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().saveEspsInfo(info);
            MessageUtils.success(session, "ESPS Marked expired.");
        } catch (Exception e) {
            MessageUtils.failure(session, "Error marking expired. Please try again later.");
            throw new ORException(e);
        }


        return m;
    }

    @RequestMapping(value = "/mark-free-trial.html", method = RequestMethod.GET)
    public ModelAndView markTrial(@RequestParam("groupId") Integer groupId, HttpSession session) {
        ModelAndView m = new ModelAndView("redirect:manage-esps.html");

        try {
            EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(groupId);
            info.setStatus(BillingStatus.FREE_TRIAL.name());
            getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().saveEspsInfo(info);
            MessageUtils.success(session, "ESPS Marked FREE Trial.");
        } catch (Exception e) {
            MessageUtils.failure(session, "Error marking Free Trial. Please try again later.");
            throw new ORException(e);
        }


        return m;
    }

    @RequestMapping(value = "/change-date.html", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam("groupId") Integer groupId, @RequestParam("endDate") String mmddyyyyEndDate, HttpSession session) {
        ModelAndView m = new ModelAndView("redirect:manage-esps.html");

        try {
            EspsBillInfo info = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getEspsBillInfoByGroupId(groupId);
            info.setEndDate(new SimpleDateFormat("MM/dd/yyyy").parse(mmddyyyyEndDate));
            getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().saveEspsInfo(info);
            MessageUtils.success(session, "Dates changed successfully.");
        } catch (Exception e) {
            MessageUtils.failure(session, "Error changing dates. Please try again later.");
            throw new ORException(e);
        }


        return m;
    }


    @RequestMapping(value = "/manage-esps.html", method = RequestMethod.GET)
    public ModelAndView manage() {
        ModelAndView m = new ModelAndView("jsp/esps-main");
        List<EspsBillInfo> infos = getBusinessFactory().getGroupBusiness().getServiceFactory().getGroupService().getAllEspsBillInfo();
        if (infos != null && !infos.isEmpty()) {
            Collections.sort(infos, new Comparator<EspsBillInfo>() {
                @Override
                public int compare(EspsBillInfo o1, EspsBillInfo o2) {
                    if (o1.getStatus().equalsIgnoreCase(o2.getStatus())) {
                        return DateTimeComparator.getDateOnlyInstance().compare(o1.getEndDate(), o2.getEndDate());
                    } else {
                        return BillingStatus.compare(BillingStatus.valueOf(o1.getStatus()), BillingStatus.valueOf(o2.getStatus()));
                    }

                }
            });
        }
        m.addObject("beans", infos);
        return m;
    }

    @RequestMapping(value = "/save-esps.html", method = RequestMethod.POST)
    public ModelAndView save(
            @RequestParam(value = "groupId") Integer groupId,
            @RequestParam(value = "accountName") String accountName,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "telPh") String telPh,
            @RequestParam(value = "billMail") String billMail,
            HttpServletRequest request,
            HttpSession session
    ) {
        ModelAndView m = new ModelAndView("redirect:esps.html");
        EspsBillInfo info = null;
        if (groupId != null) {
            info = getBusinessFactory().getGroupBusiness().getServiceFactory()
                    .getGroupService()
                    .getEspsBillInfoByGroupId(groupId);

        }
        if (info == null) {
            info = new EspsBillInfo();
            info.setGroupId(groupId);
            info.setStatus("FREE_TRIAL");
            Calendar calendar = Calendar.getInstance();
            Date currentTime = calendar.getTime();
            info.setCreatorDate(currentTime);
            info.setUserId(AppThreadLocal.getTokenLocal().getUserId());

            info.setStartDate(currentTime);
            calendar.add(Calendar.DATE, 30);
            info.setEndDate(calendar.getTime());


        }

        try {
            info.setAccountName(accountName);
            info.setBillMail(billMail);
            info.setTelPh(telPh);
            info.setTitle(title);
            info.setName(name);
            getBusinessFactory().getGroupBusiness().getServiceFactory()
                    .getGroupService()
                    .saveEspsInfo(info);
            MessageUtils.success(request.getSession(), "Billing Information Saved Successfully.");
        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), "Error saving Billing Information. Please try again");
        }
        return m;
    }

}
