package org.orlounge.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

import java.io.*;

/**
 * Created by Satyam Soni on 10/5/2015.
 */

public class AmazonS3Service {

    private static AmazonS3Service amazonS3Service = null;
    private static TransferManager transferManager = null;
    private static AmazonS3 amazonS3 = null;

    public static AmazonS3Service getInstance(boolean amazonCloudServiceFlag, String accessKey, String secretKey, String regionName) {
       // if (amazonS3Service == null) {
            amazonS3Service = new AmazonS3Service(amazonCloudServiceFlag, accessKey, secretKey, regionName);
        //}
        return amazonS3Service;
    }

    private AmazonS3Service(boolean amazonCloudServiceFlag, String accessKey, String secretKey, String regionName) {
        if (amazonCloudServiceFlag) {
            try {
                SystemPropertiesCredentialsProvider classPath = new SystemPropertiesCredentialsProvider();
                System.setProperty("aws.accessKeyId", accessKey);
                System.setProperty("aws.secretKey", secretKey);

                AWSCredentials credentials = classPath.getCredentials();
                amazonS3 = new AmazonS3Client(credentials);
                Region region = getRegionObj(regionName);
                amazonS3.setRegion(region);
                transferManager = new TransferManager(amazonS3);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private Region getRegionObj(final String regionName) {
        if (regionName.equalsIgnoreCase("US_Standard")) {
            return Region.getRegion(Regions.US_EAST_1);
        } else if (regionName.equalsIgnoreCase("Oregon")) {
            return Region.getRegion(Regions.US_WEST_2);
        } else if (regionName.equalsIgnoreCase("Northern California")) {
            return Region.getRegion(Regions.US_WEST_1);
        } else if (regionName.equalsIgnoreCase("Irealand")) {
            return Region.getRegion(Regions.EU_WEST_1);
        } else if (regionName.equalsIgnoreCase("Singapore")) {
            return Region.getRegion(Regions.AP_SOUTHEAST_1);
        } else if (regionName.equalsIgnoreCase("Tokyo")) {
            return Region.getRegion(Regions.AP_NORTHEAST_1);
        } else if (regionName.equalsIgnoreCase("Sydney")) {
            return Region.getRegion(Regions.AP_SOUTHEAST_2);
        } else if (regionName.equalsIgnoreCase("Sao Paulo")) {
            return Region.getRegion(Regions.SA_EAST_1);
        }
        return Region.getRegion(Regions.US_WEST_2);
    }

    public void uploadFile(final String bucketName, final String fileNameOnS3, final String inputFilePath) throws AmazonServiceException, AmazonClientException, InterruptedException, IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setServerSideEncryption(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        File inputFile = new File(inputFilePath);
        objectMetadata.setContentLength(inputFile.length());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFile);
            Upload upload = transferManager.upload(bucketName, fileNameOnS3, inputStream, objectMetadata);
            upload.waitForCompletion();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void uploadData(final String bucketName, final String fileNameOnS3, final String data) throws AmazonServiceException, AmazonClientException, InterruptedException, IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setServerSideEncryption(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        objectMetadata.setContentLength(data.length());
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(data.getBytes());
            Upload upload = transferManager.upload(bucketName, fileNameOnS3, inputStream, objectMetadata);
            upload.waitForCompletion();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void uploadData(final String bucketName, final String fileNameOnS3, final byte[] data) throws AmazonServiceException, AmazonClientException, InterruptedException, IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setServerSideEncryption(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
        objectMetadata.setContentLength(data.length);
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(data);
            Upload upload = transferManager.upload(bucketName, fileNameOnS3, inputStream, objectMetadata);
            upload.waitForCompletion();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void downloadFile(final String bucketName, final String fileNameOnS3, final String downloadFilePath) throws AmazonServiceException, AmazonClientException, InterruptedException {
        Download download = transferManager.download(bucketName, fileNameOnS3, new File(downloadFilePath));
        download.waitForCompletion();
        System.out.println(download.isDone());
    }

    public File downloadFileAndReturnFile(final String bucketName, final String fileNameOnS3, final String downloadFilePath) throws AmazonServiceException, AmazonClientException, InterruptedException {
        File f = new File(downloadFilePath);
        Download download = transferManager.download(bucketName, fileNameOnS3, f);
        download.waitForCompletion();
        System.out.println(download.isDone());
        return f;
    }

    public String downloadData(final String bucketName, final String fileNameOnS3) throws IOException {
        final S3Object s3object = amazonS3.getObject(new GetObjectRequest(bucketName, fileNameOnS3));
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        final StringBuffer textFile = new StringBuffer();
        try {
            inputStreamReader = new InputStreamReader(s3object.getObjectContent());
            bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                textFile.append(line).append("\n");
            }
        } finally {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return textFile.toString();
    }

    public void deleteFileFromLocal(String filePath) {
        File file = new File(filePath);
        file.delete();
    }
    
//    public void deleteFile(final String keyName,final String bucketName) {
//        final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, keyName);
//        amazonS3.deleteObject(deleteObjectRequest);
//    }

}