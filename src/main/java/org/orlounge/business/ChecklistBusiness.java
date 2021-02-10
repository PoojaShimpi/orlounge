package org.orlounge.business;

import org.orlounge.bean.ChecklistBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ORS3;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public class ChecklistBusiness extends AbstractBaseBusiness {

    public List<ChecklistBean> getChecklists() {
        try {
            List<ChecklistBean> schedules = getServiceFactory().getChecklistService().getChecklists();
            return schedules;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public ChecklistBean getChecklist(Integer id) {
        try {
            ChecklistBean schedule = getServiceFactory().getChecklistService().getChecklist(id);
            return schedule;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public File getChecklistFile(ChecklistBean bean, Integer id) {
        try {
            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            return ors3.downloadFile(bucket, bean.getFilePath(), tempFolder + "/" + bean.getName());
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ORException(ex, 0);
        }

    }

    public boolean saveChecklist(ChecklistBean bean, MultipartFile file) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();

            if (ProcessData.isValid(bean.getId())) {
                ChecklistBean schedule = getServiceFactory().getChecklistService().getChecklist(bean.getId());
                schedule.setTopic(bean.getTopic());
                schedule.setText(bean.getText());
                bean =  schedule;

            } else {
                bean.setIsActive(1);
                bean.setCreatedDate(new Date());
                bean.setCreatedId(token.getUserId());
                bean.setGroupId(token.getCurrentGroupId());

            }

            if (file != null && !file.isEmpty()) {
                bean.setName(file.getOriginalFilename());

                final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
                final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
                final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
                final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
                final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

                File dir = new File(System.getProperty("java.io.tmpdir") +File.separator +"uploads");
                if (!dir.exists()) {
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir") + File.separator+"uploads"+File.separator + token.getUserCode()).mkdir();
                }

                dir = new File(System.getProperty("java.io.tmpdir") + File.separator+"uploads"+File.separator + token.getUserCode());
                if (!dir.exists()) {
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir") + File.separator+"uploads"+File.separator + token.getUserCode()).mkdir();
                }

                File f = new File(System.getProperty("java.io.tmpdir") + File.separator+"uploads"+File.separator + token.getUserCode() + File.separator + file.getOriginalFilename());
                //File f = new File(System.getProperty("image.upload.path")+ File.separator+"uploads"+File.separator+user.getEmail()+File.separator+hospitalBadge.getName());
                f.createNewFile();
                InputStream in = null;
                FileOutputStream str = null;
                try {
                    in = file.getInputStream();
                    str = new FileOutputStream(f);
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        str.write(i);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (str != null) {
                        str.close();
                    }
                }

                ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
                String fId = StringUtil.getUUID();
                String ext = f.getName().substring(f.getName().lastIndexOf("."));
                String path = "checklists/" + fId + ext;
                bean.setFileId(fId);
                bean.setFilePath(path);

                ors3.uploadFile(f, bucket, bean.getFilePath());
                f.deleteOnExit();
            }
            getServiceFactory().getChecklistService().saveChecklist(bean);
            return true;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public boolean remvoeCheckList(String id) {
        boolean flag=false;
    	if (id != null) {
            Integer inputId = Integer.parseInt(id);
            //ChecklistBean checklistBean = getChecklist(inputId);
            //checklistBean.setIsActive(0);
    		int ans=getServiceFactory().getChecklistService().deleteChecklist(inputId);
    		if(ans > 0){
    			flag = true;
    		} else {
    			flag  = false;
    		}
        } else {
            return false;
        }
    	return flag;
    }
}
