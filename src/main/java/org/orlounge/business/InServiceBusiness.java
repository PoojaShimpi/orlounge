package org.orlounge.business;

import org.orlounge.bean.InServiceBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.DateContent;
import org.orlounge.common.util.ORS3;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public class InServiceBusiness extends AbstractBaseBusiness {

    public List<InServiceBean> getInServices() {
        try {
            List<InServiceBean> schedules = getServiceFactory().getInServiceService().getInServices();
            return schedules;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public boolean saveUpdate(InServiceBean inServiceBean, MultipartFile file) {
        try {
            InServiceBean bean = null;
            UserToken token = AppThreadLocal.getTokenLocal();
            if (ProcessData.isValid(inServiceBean.getId())) {
                bean = getInService(inServiceBean.getId());
                bean.setTopic(inServiceBean.getTopic());
                bean.setDate(inServiceBean.getDate());
                bean.setGroupId(token.getCurrentGroupId());
                bean.setText(inServiceBean.getText());
                bean.setPresenter(inServiceBean.getPresenter());

            } else {
                bean = new InServiceBean();
                bean.setTopic(inServiceBean.getTopic());
                bean.setDate(inServiceBean.getDate());
                bean.setGroupId(token.getCurrentGroupId());
                bean.setText(inServiceBean.getText());
                bean.setPresenter(inServiceBean.getPresenter());
                bean.setRole(token.getRole());
                bean.setCreatedDate(DateContent.convertCurrentLocalDateIntoTmizoneDate(token.getTimeZone()));
                bean.setCreatedId(token.getUserId());
                bean.setIsActive(1);
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
                    new File(System.getProperty("java.io.tmpdir") +File.separator +"uploads"+File.separator + token.getUserCode()).mkdir();
                }

                dir = new File(System.getProperty("java.io.tmpdir") +File.separator+"uploads"+File.separator + token.getUserCode());
                if (!dir.exists()) {
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir") +File.separator+"uploads"+File.separator + token.getUserCode()).mkdir();
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
                String path = "in-service/" + fId + ext;
                bean.setFileId(fId);
                bean.setFilePath(path);
                bean.setFileType(file.getContentType());

                ors3.uploadFile(f, bucket, bean.getFilePath());
                f.deleteOnExit();
            }
            getServiceFactory().getInServiceService().saveInService(bean);
            return true;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public InServiceBean getInService(Integer id) {
        try {
            InServiceBean schedule = getServiceFactory().getInServiceService().getInService(id);
            return schedule;
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    public File getInServiceFile(InServiceBean manual, Integer id) {
        try {
            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            return ors3.downloadFile(bucket, manual.getFilePath(), tempFolder + "/" + manual.getName());
        } catch (ORException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ORException(ex, 0);
        }

    }

    public boolean removeInservice(String id) {
        if (id != null) {
             Integer inputId = Integer.parseInt(id);
             InServiceBean inServiceBean = getInService(inputId);
             inServiceBean.setIsActive(0);
             getServiceFactory().getInServiceService().saveInService(inServiceBean);
             return true;
        } else {
            return false;
        }
    }
}
