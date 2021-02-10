package org.orlounge.factory;

import lombok.Getter;
import org.orlounge.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Component
@Getter
public class DAOFactory {

    @Autowired
    private LoginDAO loginDAO;

    @Autowired
    private ForumDAO forumDAO;

    @Autowired
    private ForumCommentDAO forumCommentDAO;

    @Autowired
    private HospitalDAO hospitalDAO;
    @Autowired
    private StateDAO stateDAO;


    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private UserGroupMapDAO userGroupMapDAO;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TmpOtpDAO tmpOtpDAO;
    @Autowired
    private ApproverResponseDAO approverResponseDAO;
    @Autowired
    private BulkMailProcessDAO bulkMailProcessDAO;

    @Autowired
    private LastAccessGroupDAO lastAccessGroupDAO;


    @Autowired
    private StaffInfoDAO staffInfoDAO;


    @Autowired
    private MsgBoardDAO msgBoardDAO;
    @Autowired
    private MsgBoardCommentDAO msgBoardCommentDAO;



    @Autowired
    private CallScheduleDAO callScheduleDAO;



    @Autowired
    private ProcedureManualDAO procedureManualDAO;


    @Autowired
    private NoticeDAO noticeDAO;


    @Autowired
    private InServiceDAO inServiceDAO;



    @Autowired
    private PostOpDAO postOpDAO;
    @Autowired
    private PostOpAccessDAO postOpAccessDAO;

    @Autowired
    private ChecklistDAO checklistDAO;

    @Autowired
    private HandoverDAO handoverDAO;


    @Autowired
    private EventLogDAO eventLogDAO;

    @Autowired
    private PrefListDAO prefListDAO;
    @Autowired
    private InstrumentPrefListDAO instrumentPrefListDAO;

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private ProjectAccessDAO projectAccessDAO;
    @Autowired
    private ProjectDocDAO projectDocDAO;

    @Autowired
    private AnaethsiaSetupDAO anaethsiaSetupDAO;
    @Autowired
    private AnaethsiaNewSetupDAO anaethsiaNewSetupDAO;

    @Autowired
    private GeoLocationDAO geoLocationDAO;
    @Autowired
    private EspsBillInfoDAO espsBillInfoDAO;

    @Autowired
    private VoteDAO voteDAO;
    @Autowired
    private VoteIdeaDAO voteIdeaDAO;
    @Autowired
    private IdeaDAO ideaDAO;

    @Autowired
    private TagDAO tagDAO;


}
