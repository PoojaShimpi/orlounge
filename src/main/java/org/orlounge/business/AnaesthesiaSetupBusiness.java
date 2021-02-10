package org.orlounge.business;

import org.orlounge.bean.AnaethesiaSetupBean;
import org.orlounge.bean.AnaethesiaSetupNewBean;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Satyam Soni on 1/4/2016.
 */
@Component
public class AnaesthesiaSetupBusiness extends AbstractBaseBusiness {
    public List<AnaethesiaSetupBean> getAllSetups() {
        return getServiceFactory().getAnaesthesiaService().getAllSetups();
    }

    public AnaethesiaSetupBean getSetupById(final Integer id) {
        return getServiceFactory().getAnaesthesiaService().getSetupById(id);
    }



    public File getSetupFile(AnaethesiaSetupBean bean, Integer id){
        try{
            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            ORS3 ors3 = ORS3.getInstance(accessKey, secretKey, region);
            return ors3.downloadFile(bucket, bean.getFilePath(), tempFolder+"/"+bean.getName());
        } catch (ORException ex){
            throw ex;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new ORException(ex,0);
        }

    }



    public AnaethesiaSetupBean saveSetup(AnaethesiaSetupBean bean, MultipartFile file) throws IOException {

        UserToken token = AppThreadLocal.getTokenLocal();

        if(file != null && !file.isEmpty()){
            bean.setName(file.getOriginalFilename());


            final String bucket = getConfigMap().get(AppConstants.CALL_SCHEDULE_BUCKET_NAME);
            final String accessKey = getConfigMap().get(AppConstants.S3_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.S3_SECRET_KEY_PROP);
            final String region = getConfigMap().get(AppConstants.S3_REGION_PROP);
            final String tempFolder = getConfigMap().get(AppConstants.CALL_SCHEDULE_TEMP_DOWNLOAD);

            File dir = new File(System.getProperty("java.io.tmpdir")+"\\uploads");
            if(!dir.exists()){
                dir.mkdir();
                new File(System.getProperty("java.io.tmpdir")+"\\uploads\\"+ token.getUserCode()).mkdir();
            }

            dir = new File(System.getProperty("java.io.tmpdir")+"\\uploads\\"+ token.getUserCode());
            if(!dir.exists()){
                dir.mkdir();
                new File(System.getProperty("java.io.tmpdir")+"\\uploads\\"+ token.getUserCode()).mkdir();
            }

            File f = new File(System.getProperty("java.io.tmpdir")+"\\uploads\\"+ token.getUserCode()+"\\"+ file.getOriginalFilename());
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
            String path = "setups/"+ fId+ext;
            bean.setFileId(fId);
            bean.setFilePath(path);

            boolean success = ors3.uploadFile(f, bucket, bean.getFilePath());
            f.deleteOnExit();


        }

        return getServiceFactory().getAnaesthesiaService().saveSetup(bean);
    }


    public  List<AnaethesiaSetupNewBean> getAllAnaethesiaSetupNewBean(){
        return getServiceFactory().getAnaesthesiaService().getAllAnaethesiaSetupNewBean();
    }

    public AnaethesiaSetupNewBean getAnaethesiaSetupNewBeanById(Integer id){
        return  getServiceFactory().getAnaesthesiaService().getAnaethesiaSetupNewBeanById(id);
    }


    public boolean saveOrUpdateAnaethesiaSetupNewBean(AnaethesiaSetupNewBean bean,boolean isUpdate){


       return getServiceFactory().getAnaesthesiaService().saveAnaethesiaSetupNewBean(bean,isUpdate);
    }

    public boolean removeAnaethesiaSetupNewBean(Integer id){
        AnaethesiaSetupNewBean anaethesiaSetupNewBean = getAnaethesiaSetupNewBeanById(id);
        anaethesiaSetupNewBean.setActive(0);
        return getServiceFactory().getAnaesthesiaService().saveAnaethesiaSetupNewBean(anaethesiaSetupNewBean,true);
    }

}
