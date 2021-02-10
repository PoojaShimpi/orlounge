package org.orlounge.service;

import org.orlounge.bean.NoticeBean;
import org.orlounge.common.AppThreadLocal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class NoticeService extends AbstractBaseService {


    public List<NoticeBean> getNotices(){
            return getDaoFactory().getNoticeDAO().getNotices(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public NoticeBean getNotice(Integer id){
            return getDaoFactory().getNoticeDAO().findById(id).get();
    }

    public NoticeBean saveNotice(NoticeBean bean){
        return getDaoFactory().getNoticeDAO().save(bean);
    }

    public NoticeBean deleteNotice(NoticeBean bean){
        return getDaoFactory().getNoticeDAO().deleteNotice(bean);
    }
    public NoticeBean deleteNotice(Integer id){
        return getDaoFactory().getNoticeDAO().deleteNotice(id);
    }
}
