package org.orlounge.factory;

import lombok.Getter;
import lombok.Setter;
import org.orlounge.business.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 6/20/2015.
 */
@Component
@Getter
@Setter
public class BusinessFactory {

    @Autowired
    private TestBusiness testBusiness;


    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private ForumBusiness forumBusiness;


    @Autowired
    private MessageBoardBusiness messageBoardBusiness;


    @Autowired
    private CallScheduleBusiness callScheduleBusiness;


    @Autowired
    private ProcedureManualBusiness procedureManualBusiness;


    @Autowired
    private NoticeBusiness noticeBusiness;


    @Autowired
    private InServiceBusiness inServiceBusiness;


    @Autowired
    private StaffBusiness staffBusiness;


    @Autowired
    private PostOpBusiness postOpBusiness;


    @Autowired
    private GroupBusiness groupBusiness;


    @Autowired
    private HospitalBusiness hospitalBusiness;


    @Autowired
    private ChecklistBusiness checklistBusiness;


    @Autowired
    private HandoverBusiness handoverBusiness;

    @Autowired
    private EventBusiness eventBusiness;


    @Autowired
    private MailBusiness mailBusiness;


    @Autowired
    private PrefListBusiness prefListBusiness;


    @Autowired
    private ProjectBusiness projectBusiness;


    @Autowired
    private AnaesthesiaSetupBusiness anaesthesiaSetupBusiness;


    @Autowired
    private TmpOtpBusiness tmpOtpBusiness;

    @Autowired
    private VoteBusiness voteBusiness;


}



