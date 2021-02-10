
package org.orlounge.business;

import org.orlounge.common.AppConstants;
import org.orlounge.common.mail.ORMail;
import org.orlounge.common.util.Validation;
import org.orlounge.exception.ORException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Satyam Soni on 11/8/2015.
 */
@Component
public class MailBusiness extends AbstractBaseBusiness{
    @Autowired
    private ORMail orMail;

    public ORMail getOrMail() {
        return orMail;
    }

    public void setOrMail(ORMail orMail) {
        this.orMail = orMail;
    }


    public void sendMail(String to,
                         String bcc,
                         String cc,
                         String subject,
                         String body,
                         MultipartFile file,
                         String fromMail,
                         String fromPass){
        try{
            List<String> tos = new ArrayList<String>();
            List<String> ccs = new ArrayList<String>();
            List<String> bccs = new ArrayList<String>();
            String sub = "";
            String bodyTxt = "";
            String temp;
            Iterator<String> it;
            if(to != null && !to.isEmpty()){
                tos = Arrays.asList(to.split(","));
                it = tos.iterator();
                while(it.hasNext()){
                    if(!Validation.validateEmail(it.next())){
                        it.remove();
                    }
                }
            }
            if(bcc != null && !bcc.isEmpty()){
                bccs = Arrays.asList(bcc.split(","));
                it = bccs.iterator();
                while(it.hasNext()){
                    if(!Validation.validateEmail(it.next())){
                        it.remove();
                    }
                }
            }
            if(cc != null && !cc.isEmpty()){
                ccs = Arrays.asList(cc.split(","));
                it = ccs.iterator();
                while(it.hasNext()){
                    if(!Validation.validateEmail(it.next())){
                        it.remove();
                    }
                }
            }

            if(subject != null && !subject.isEmpty()){
                sub = subject.trim();
            }

            if(body != null && !body.isEmpty()){
                bodyTxt = body.trim();
            }

            File f = null;
            if(file != null && !file.isEmpty()) {

                File dir = new File(System.getProperty("java.io.tmpdir") + "\\uploads");
                if (!dir.exists()) {
                    dir.mkdir();
                    new File(System.getProperty("java.io.tmpdir") + "\\uploads").mkdir();
                }

                f = new File(System.getProperty("java.io.tmpdir") + "\\uploads\\" + file.getOriginalFilename());
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

            }
            final String host = getConfigMap().get(AppConstants.SMTP_HOST);
            final String port = getConfigMap().get(AppConstants.SMTP_PORT);
            final String accessKey = getConfigMap().get(AppConstants.SES_ACCESS_KEY_PROP);
            final String secretKey = getConfigMap().get(AppConstants.SES_SECRET_KEY_PROP);

            final ORMail.MailConfig mailConfig = new ORMail.MailConfig( fromPass,fromMail, port, host, accessKey, secretKey);
            getOrMail().sendMail(mailConfig, sub, bodyTxt, tos, ccs, bccs, f, true);
        }catch ( Exception ex){
            throw new ORException(ex,0);
        }
    }


    public void sendMail(String to,
                         String bcc,
                         String cc,
                         String subject,
                         String body,
                         MultipartFile file){

            final String fromMail = getConfigMap().get(AppConstants.FROM_MAIL);
            final String fromPass = getConfigMap().get(AppConstants.FROM_PASS);

            sendMail(to, bcc, cc, subject, body, file, fromMail, fromPass);
    }
}
