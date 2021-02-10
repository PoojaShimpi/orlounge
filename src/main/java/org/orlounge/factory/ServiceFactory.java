package org.orlounge.factory;

import lombok.Getter;
import lombok.Setter;
import org.orlounge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Service
@Getter
@Setter
public class ServiceFactory {
    @Autowired
    private LoginService loginService;



    @Autowired
    private ForumService forumService;

    @Autowired
    private MessageBoardService messageBoardService;


    @Autowired
    private HospitalService hospitalService;



    @Autowired
    private GroupService groupService;


    @Autowired
    private StaffInfoService staffInfoService;



    @Autowired
    private CallScheduleService callScheduleService;



    @Autowired
    private ProcedureManualService procedureManualService;


    @Autowired
    private NoticeService noticeService;




    @Autowired
    private InServiceService inServiceService;


    @Autowired
    private PostOpService postOpService;



    @Autowired
    private ChecklistService checklistService;


    @Autowired
    private HandoverService handoverService;



    @Autowired
    private EventService eventService;


    @Autowired
    private PrefListService prefListService;


    @Autowired
    private ProjectService projectService;

    @Autowired
    private AnaesthesiaService anaesthesiaService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private TagsService tagsService;


}
