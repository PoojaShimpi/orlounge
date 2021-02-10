package org.orlounge.business;

import org.orlounge.bean.InstrumentPrefListBean;
import org.orlounge.bean.PrefListBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ORS3;
import org.orlounge.common.util.StringUtil;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Component
public class PrefListBusiness extends AbstractBaseBusiness{

    public List<PrefListBean> getAllPrefs() {
        try{
            return getServiceFactory().getPrefListService().getAllPrefs();
        }catch (ORException ex){
            throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }



    public File getFileFromAWS(String filePath,String fileName){
        try{
            //CallScheduleBean schedule = getServiceFactory().getCallScheduleService().getCallSchedule(id);


            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);;
            final String region = "us-east-1";
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            //  AmazonS3Service amazonS3Service = AmazonS3Service.getInstance(true, accessKey, secretKey, region);
            // return amazonS3Service.downloadFileAndReturnFile(bucket, "Satyam-J2EE-Developer.doc", "d:\\Logs\\test.doc");
            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            return ors3.downloadFile(bucket, filePath, tempFolder+"/"+fileName);
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ORException(ex,0);
        }

    }

    public PrefListBean getPrefById(final Integer prefId) {
        try{
            return getServiceFactory().getPrefListService().getPrefById(prefId);
        }catch (ORException ex){
            throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }

    public PrefListBean savePrefList(PrefListBean bean) {
        try{
            return getServiceFactory().getPrefListService().savePrefList(bean);
        }catch (ORException ex){
            throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }
    public InstrumentPrefListBean deleteIns(InstrumentPrefListBean bean){

        try{

            return getServiceFactory().getPrefListService().deleteIns(bean);
        }catch (ORException ex){
            throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
    }

    public File downloadFile(String path){

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
            return ors3.downloadFile(bucket, path, tempFolder+"/"+"sampleInstrument.csv");
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ORException(ex,0);
        }
    }


    public InstrumentPrefListBean saveIns(InstrumentPrefListBean bean, MultipartFile file){

        try{

            UserToken token  = AppThreadLocal.getTokenLocal();
            if(file != null && !file.isEmpty()){

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
                String path = "preference-list/"+ fId+ext;

                bean.setPhotoImagePath(path);

                boolean success = ors3.uploadFile(f, bucket, bean.getPhotoImagePath());
                f.deleteOnExit();

            }
            if((bean.getName() !=null && !bean.getName().isEmpty())
                    || (bean.getQuantity() != null && !bean.getQuantity().isEmpty())
                    || (bean.getBin() != null && !bean.getBin().isEmpty())
                    || (bean.getCatalog() != null && !bean.getCatalog().isEmpty())
                    || (bean.getPhotoImagePath() != null && !bean.getPhotoImagePath().isEmpty())){
              return getServiceFactory().getPrefListService().saveIns(bean);
            }

        }catch (ORException ex){
            throw ex;
        } catch (Exception ex){
            throw new ORException(ex, 0);
        }
        return null;
    }

    public boolean deletePreflist(String id){
        if(id!=null){
            try {
                PrefListBean prefListBean = getPrefById(Integer.parseInt(id));
                prefListBean.setIsActive(0);
                getBusinessFactory().getPrefListBusiness().savePrefList(prefListBean);
                return true;
            } catch (Exception e){
                return false;
            }
        } else{
            return false;
        }
    }
}
