package org.orlounge.business;

import org.orlounge.bean.CallScheduleBean;
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
public class CallScheduleBusiness extends AbstractBaseBusiness{

    public List<CallScheduleBean>  getCallSchedules(){
        try{
            List<CallScheduleBean> schedules = getServiceFactory().getCallScheduleService().getCallSchedules();
            return schedules;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }
    public CallScheduleBean  getCallSchedule(Integer id){
        try{
            CallScheduleBean schedule = getServiceFactory().getCallScheduleService().getCallSchedule(id);
            return schedule;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }


    public boolean  saveSchedule(final String topic, final String id, final String text,  MultipartFile file){
        try{
            CallScheduleBean schedule;
            UserToken token = AppThreadLocal.getTokenLocal();
            boolean success = true;
            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            //  AmazonS3Service amazonS3Service = AmazonS3Service.getInstance(true, accessKey, secretKey, region);
           // return amazonS3Service.downloadFileAndReturnFile(bucket, "Satyam-J2EE-Developer.doc", "d:\\Logs\\test.doc");
            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            if(ProcessData.isValid(id)){
               schedule = getServiceFactory().getCallScheduleService().getCallSchedule(Integer.parseInt(id));
               String filepath=schedule.getFilePath();
         		if(filepath != null)
        		{
            		//String[] arrOfStr = filepath.split("/"); 
            		ors3.deleteFile(filepath, bucket);	
        		}
                schedule.setTopic(topic);
                schedule.setText(text);
            }
            else {

               schedule = new CallScheduleBean();
                schedule.setTopic(topic);
                schedule.setText(text);
//                schedule.setIsActive(1);
                schedule.setCreatedDate(new Date());
                schedule.setRole(token.getRole());
                schedule.setCreatedId(token.getUserId());
                schedule.setGroupId(token.getCurrentGroupId());
            }

            if(file!=null && !file.isEmpty()){
                File dir = new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads");
                if(!dir.exists()){
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+ token.getUserCode()).mkdir();
                }

                dir = new File(System.getProperty("java.io.tmpdir")+File.separator+"uploads"+File.separator+token.getUserCode());
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
                    String fId = StringUtil.getUUID();
                    String ext = f.getName().substring(f.getName().lastIndexOf("."));
                    String path = "call-schedules/"+ fId+ext;
                    schedule.setName(file.getOriginalFilename());
                    schedule.setFileId(fId);
                    schedule.setFilePath(path);
                    schedule.setFileType(file.getContentType());
                    success = ors3.uploadFile(f, bucket, schedule.getFilePath());
                    f.deleteOnExit();
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

            }
            if(success){
                getServiceFactory().getCallScheduleService().saveCallSchedule(schedule);
                return true;
            }

            return false;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }


    public File getCallScheduleFile(CallScheduleBean schedule,Integer id){
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
           return ors3.downloadFile(bucket, schedule.getFilePath(), tempFolder+"/"+schedule.getName());
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ORException(ex,0);
        }

    }
    
    

    public boolean deleteCallSchedule(String id){
    	boolean flag = true;
    	try {

            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

          //  AmazonS3Service amazonS3Service = AmazonS3Service.getInstance(true, accessKey, secretKey, region);
           // return amazonS3Service.downloadFileAndReturnFile(bucket, "Satyam-J2EE-Developer.doc", "d:\\Logs\\test.doc");
            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
    		Integer inputId = Integer.parseInt(id);
    		CallScheduleBean callScheduleBean = getServiceFactory().getCallScheduleService().getCallSchedule(inputId);
    		String filepath=callScheduleBean.getFilePath();
    		if(filepath != null)
    		{
        		//String[] arrOfStr = filepath.split("/"); 
        		ors3.deleteFile(filepath, bucket);	
    		}
    		int ans=getServiceFactory().getCallScheduleService().deleteCallSchedule(inputId);
    		if(ans > 0){
    			flag = true;
    		} else {
    			flag  = false;
    		}
		} catch (Exception e) {
			flag  = false;
            e.printStackTrace();
            throw new ORException(e,0);
		}
    	return flag;
    }
}
