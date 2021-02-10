package org.orlounge.service;

import org.orlounge.bean.PostOpAccessBean;
import org.orlounge.bean.PostOpBean;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Transactional
@Service
public class PostOpService extends AbstractBaseService{


    public List<PostOpBean> getAccessiblePostOps() {
        UserToken token = AppThreadLocal.getTokenLocal();

        return getDaoFactory().getPostOpDAO().getAccessiblePostOps(token.getUserId(), token.getCurrentGroupId(), token.getRole());
    }

    public List<PostOpBean> getPostOps(){
            return getDaoFactory().getPostOpDAO().getPostOps(AppThreadLocal.getTokenLocal().getCurrentGroupId());
    }

    public PostOpBean getPostOp(Integer id){
            return getDaoFactory().getPostOpDAO().findById(id).get();
    }

    public PostOpBean savePostOp(PostOpBean bean){
        return getDaoFactory().getPostOpDAO().save(bean);
    }

    public PostOpBean deletePostOp(PostOpBean bean){
    	//int ans=getDaoFactory().getPostOpDAO().deletePostOpAccess(id); 	
    	return getDaoFactory().getPostOpDAO().deletePostOp(bean);
    }

    public PostOpAccessBean savePostOpAccess(PostOpAccessBean accessBean) {
        return getDaoFactory().getPostOpAccessDAO().save(accessBean);
    }
}
