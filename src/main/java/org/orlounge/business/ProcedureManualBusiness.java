package org.orlounge.business;

import org.orlounge.bean.ProcedureManualBean;
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
public class ProcedureManualBusiness extends AbstractBaseBusiness{

    public List<ProcedureManualBean>  getProcedureManuals(){
        try{
            List<ProcedureManualBean> schedules = getServiceFactory().getProcedureManualService().getProcedureManuals();
            return schedules;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }
    public ProcedureManualBean  getProcedureManual(Integer id){
        try{
            ProcedureManualBean schedule = getServiceFactory().getProcedureManualService().getProcedureManual(id);
            return schedule;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }


    public File getProcedureManualFile(ProcedureManualBean manual,Integer id){
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




    public boolean  saveManual(final String topic, final String id, String text,  MultipartFile file){
    	boolean success = true;
        try{
            ProcedureManualBean schedule=null;
            UserToken token = AppThreadLocal.getTokenLocal();

            if(ProcessData.isValid(id)){
                schedule = getServiceFactory().getProcedureManualService().getProcedureManual(Integer.parseInt(id));
                schedule.setTopic(topic);
                schedule.setText(text);
            } else {
                schedule = new ProcedureManualBean();
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
                String path = "manual-procedure/"+ fId+ext;
                schedule.setFileId(fId);
                schedule.setFilePath(path);
                schedule.setFileType(file.getContentType());

                success = ors3.uploadFile(f, bucket, schedule.getFilePath());
            }
            if(success){
                getServiceFactory().getProcedureManualService().saveProcedureMaunal(schedule);
                return true;
            }
            return false;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
    }

    public boolean removeProcedureManualBean(String id){
        boolean flag = false;
        Integer inputId = Integer.parseInt(id);
        ProcedureManualBean manualBean = getProcedureManual(inputId);
        manualBean.setIsActive(0);
        try{
            getServiceFactory().getProcedureManualService().saveProcedureMaunal(manualBean);
            flag = true;
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            throw new ORException(ex,0);
        }
        return flag;
    }
}
