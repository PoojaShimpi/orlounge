package org.orlounge.business;

import org.orlounge.bean.NoticeBean;
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
public class NoticeBusiness extends AbstractBaseBusiness{

    public List<NoticeBean>  getNotices(){
        try{
            List<NoticeBean> schedules = getServiceFactory().getNoticeService().getNotices();
            return schedules;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }
    public NoticeBean  getNotice(Integer id){
        try{
            NoticeBean schedule = getServiceFactory().getNoticeService().getNotice(id);
            return schedule;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }


    public File getNoticeFile(NoticeBean manual,Integer id){
        try{
            //CallScheduleBean schedule = getServiceFactory().getCallScheduleService().getCallSchedule(id);

            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

          //  AmazonS3Service amazonS3Service = AmazonS3Service.getInstance(true, accessKey, secretKey, region);
           // return amazonS3Service.downloadFileAndReturnFile(bucket, "Satyam-J2EE-Developer.doc", "d:\\Logs\\test.doc");
            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
           return ors3.downloadFile(bucket, manual.getFilePath(), tempFolder+"/"+manual.getName());
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ORException(ex,0);
        }

    }






    public boolean  saveNotice(final String topic, final Integer id, String text,  MultipartFile file){
        try{
            NoticeBean schedule=null;
            UserToken token = AppThreadLocal.getTokenLocal();
            boolean success = true;
            if(ProcessData.isValid(id)){
                schedule = getServiceFactory().getNoticeService().getNotice(id);
                schedule.setTopic(topic);
                schedule.setText(text);
            } else {
                schedule = new NoticeBean();
                schedule.setTopic(topic);
                schedule.setIsActive(1);
                schedule.setCreatedDate(new Date());
                schedule.setRole(token.getRole());
                schedule.setCreatedId(token.getUserId());
                schedule.setGroupId(token.getCurrentGroupId());
                schedule.setText(text);
            }


            if(file != null && !file.isEmpty()){
                schedule.setName(file.getOriginalFilename());
                final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
                final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
                final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
                final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
                final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

                File dir = new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads");
                if(!dir.exists()){
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+ token.getUserCode()).mkdir();
                }

                dir = new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+ token.getUserCode());
                if(!dir.exists()){
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+ token.getUserCode()).mkdir();
                }

                File f = new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+ token.getUserCode()+File.separator+ file.getOriginalFilename());
                //File f = new File(System.getProperty("image.upload.path")+ File.separator+"uploads"+File.separator+user.getEmail()+File.separator+hospitalBadge.getName());
                f.createNewFile();
                InputStream in = null;
                FileOutputStream str = null;
                try{
                    in = file.getInputStream();
                    str = new FileOutputStream(f);
                    int  i =0 ;
                    while((i = in.read()) != -1){
                        str.write(i);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    if(in != null){
                        in.close();
                    }
                    if(str != null){
                        str.close();
                    }
                }
                ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
                String fId = StringUtil.getUUID();
                String ext = f.getName().substring(f.getName().lastIndexOf("."));
                String path = "notices/"+ fId+ext;
                schedule.setFileId(fId);
                schedule.setFilePath(path);
                schedule.setFileType(file.getContentType());

                success = ors3.uploadFile(f, bucket, schedule.getFilePath());
                f.deleteOnExit();
            }
            if(success){
                getServiceFactory().getNoticeService().saveNotice(schedule);
                return true;
            }
            return false;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }

    public boolean deleteNotice(String id){
        boolean flag = false;
        try {
            Integer inputId = Integer.parseInt(id);
            getServiceFactory().getNoticeService().deleteNotice(inputId);
        } catch (Exception e) {
            flag  = false;
            throw new ORException(e,0);
        }
        return flag;
    }

}
