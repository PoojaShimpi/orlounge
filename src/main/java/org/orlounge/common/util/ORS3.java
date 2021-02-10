package org.orlounge.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.*;

/**
 * Created by Satyam Soni on 10/7/2015.
 */
public class ORS3 {
    private static ORS3 me;
    private AmazonS3 s3;

    private ORS3(String accessKey, String secretKey, Regions region) {
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretKey", secretKey);
        s3 = new AmazonS3Client();
        Region usWest2 = Region.getRegion(region);
        s3.setRegion(usWest2);
    }

    public void deleteFile(final String keyName,final String bucketName) {
        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, keyName);
        s3.deleteObject(deleteObjectRequest);
    }

    public static ORS3 getInstance(String accessKey, String secretKey, String region) {
        if (me != null) {
            return me;
        }
        Regions r;
        if ("US_East".equalsIgnoreCase(region)) {
            r = Regions.US_EAST_1;
        } else if ("US_West1".equalsIgnoreCase(region)) {
            r = Regions.US_WEST_1;
        } else if ("US_West2".equalsIgnoreCase(region)) {
            r = Regions.US_WEST_2;
        } else {
            r = Regions.US_EAST_1;

        }
        /*System.out.println(accessKey.equals("AKIAIG4IVVTHLNA4UAZA"));
        System.out.println(secretKey.equals("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"));
        accessKey = "AKIAIG4IVVTHLNA4UAZA";
        secretKey = "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";*/
        me = new ORS3(accessKey, secretKey, r);
        return me;


    }

    public boolean uploadFile(File f, String bucketName, String s3FilePath) {
        //String key = "MyObjectKey";
        s3.putObject(new PutObjectRequest(bucketName, s3FilePath, f));
        return true;
    }

    public File downloadFile(String bucketName, String s3FilePath, String filePath) throws IOException {


        System.out.println("Downloading an object");
        S3Object object = s3.getObject(new GetObjectRequest(bucketName, s3FilePath));
        System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());

        OutputStream outputStream = null;
        File f = new File(filePath);
        File parentDirectory = f.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(f));
            byte[] buffer = new byte[1024 * 10];
            int bytesRead;
            while ((bytesRead = object.getObjectContent().read(buffer)) > -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            object.getObjectContent().abort();
            throw new AmazonClientException(
                    "Unable to store object contents to disk: " + e.getMessage(), e);
        } finally {
            try {
                outputStream.close();
            } catch (Exception e) {
            }
            try {
                object.getObjectContent().close();
            } catch (Exception e) {
            }
        }

        return f;
    }
}
