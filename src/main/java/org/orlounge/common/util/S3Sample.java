package org.orlounge.common.util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.*;

/**
 * Created by Satyam Soni on 10/7/2015.
 */
public class S3Sample {

    public static class ORS3Test {
        private static ORS3Test me;
        private AmazonS3 s3 ;

        private ORS3Test(String accessKey, String secretKey, Regions region){
            System.setProperty("aws.accessKeyId", accessKey);
            System.setProperty("aws.secretKey", secretKey);
            s3 = new AmazonS3Client();
            Region usWest2 = Region.getRegion(region);
            s3.setRegion(usWest2);
        }


        public static ORS3Test getInstance(String accessKey, String secretKey, String region){
            if(me != null){
                return me;
            }
            Regions r ;
            if("US_East".equalsIgnoreCase(region)) {
                r = Regions.US_EAST_1;
            } else if("US_West1".equalsIgnoreCase(region)){
                r  = Regions.US_WEST_1;
            } else if("US_West2".equalsIgnoreCase(region)){
                r  = Regions.US_WEST_2;
            } else {
                r = Regions.US_EAST_1;

            }

            me = new ORS3Test(accessKey ,secretKey, r);
            return me;


        }

        public boolean uploadFile(File f, String bucketName, String fileName){
            //String key = "MyObjectKey";
            s3.putObject(new PutObjectRequest(bucketName, fileName, f));
            return true;
        }

        public File downloadFile(String bucketName, String s3FilePath,  String filePath) throws IOException{



            System.out.println("Downloading an object");

            S3Object object = s3.getObject(new GetObjectRequest(bucketName, s3FilePath));
            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());

            OutputStream outputStream = null;
            File f = new File(filePath);
            File parentDirectory = f.getParentFile();
            if ( parentDirectory != null && !parentDirectory.exists() ) {
                parentDirectory.mkdirs();
            }
            try {
                outputStream = new BufferedOutputStream(new FileOutputStream(f));
                byte[] buffer = new byte[1024*10];
                int bytesRead;
                while ((bytesRead = object.getObjectContent().read(buffer)) > -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                object.getObjectContent().abort();
                throw new RuntimeException(
                        "Unable to store object contents to disk: " + e.getMessage(), e);
            } finally {
                try {outputStream.close();} catch (Exception e) {}
                try {object.getObjectContent().close();} catch (Exception e) {}
            }

            return f;
        }
    }

    public static void main(String[] args) throws IOException {
        /*
         * Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users)
         * and save the following lines after replacing the underlined values with your own.
         *
         * [default]
         * aws_access_key_id = YOUR_ACCESS_KEY_ID
         * aws_secret_access_key = YOUR_SECRET_ACCESS_KEY
         */

        System.setProperty("aws.accessKeyId", "AKIAIG4IVVTHLNA4UAZA");
        System.setProperty("aws.secretKey", "hOJiFDoAawhTRPjlb9wW7I8P1jFo9jKN2+0XMYe1");
        AmazonS3 s3 = null;
        /*AmazonS3 s3 = new AmazonS3Client();
        Region usWest2 = Region.getRegion(Regions.US_EAST_1);
        s3.setRegion(usWest2);
*/
        String bucketName = "call-schedule-dev";
        String key = "MyObjectKey";

        System.out.println("===========================================");
        System.out.println("Getting Started with Amazon S3");
        System.out.println("===========================================\n");

        try {
            /*
             * Create a new S3 bucket - Amazon S3 bucket names are globally unique,
             * so once a bucket name has been taken by any user, you can't create
             * another bucket with that same name.
             *
             * You can optionally specify a location for your bucket if you want to
             * keep your data closer to your applications or users.
             */
            //System.out.println("Creating bucket " + bucketName + "\n");
            //s3.createBucket(bucketName);

            /*
             * List the buckets in your account
             */
        /*    System.out.println("Listing buckets");
            for (Bucket bucket : s3.listBuckets()) {
                System.out.println(" - " + bucket.getName());
            }
            System.out.println();
          */
            ORS3Test or = ORS3Test.getInstance("AKIAIG4IVVTHLNA4UAZA", "hOJiFDoAawhTRPjlb9wW7I8P1jFo9jKN2+0XMYe1", "US_East1");
            //or.uploadFile(new File("C:\\Users\\dell\\Desktop\\ezVR-future.txt"), bucketName, key);
            File f = or.downloadFile(bucketName ,"1/"+key ,"C:\\Users\\dell\\Desktop\\ezVR-future-s3.txt");
            System.exit(0);

            /*
             * Upload an object to your bucket - You can easily upload a file to
             * S3, or upload directly an InputStream if you know the length of
             * the data in the stream. You can also specify your own metadata
             * when uploading to S3, which allows you set a variety of options
             * like content-type and content-encoding, plus additional metadata
             * specific to your applications.
             */
            System.out.println("Uploading a new object to S3 from a file\n");
            s3.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));

            /*
             * Download an object - When you download an object, you get all of
             * the object's metadata and a stream from which to read the contents.
             * It's important to read the contents of the stream as quickly as
             * possibly since the data is streamed directly from Amazon S3 and your
             * network connection will remain open until you read all the data or
             * close the input stream.
             *
             * GetObjectRequest also supports several other options, including
             * conditional downloading of objects based on modification times,
             * ETags, and selectively downloading a range of an object.
             */
            System.out.println("Downloading an object");
            S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));
            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
            displayTextInputStream(object.getObjectContent());

            /*
             * List objects in your bucket by prefix - There are many options for
             * listing the objects in your bucket.  Keep in mind that buckets with
             * many objects might truncate their results when listing their objects,
             * so be sure to check if the returned object listing is truncated, and
             * use the AmazonS3.listNextBatchOfObjects(...) operation to retrieve
             * additional results.
             */
            System.out.println("Listing objects");
            ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
                    .withBucketName(bucketName)
                    .withPrefix("My"));
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                System.out.println(" - " + objectSummary.getKey() + "  " +
                        "(size = " + objectSummary.getSize() + ")");
            }
            System.out.println();

            /*
             * Delete an object - Unless versioning has been turned on for your bucket,
             * there is no way to undelete an object, so use caution when deleting objects.
             */
            System.out.println("Deleting an object\n");
            s3.deleteObject(bucketName, key);

            /*
             * Delete a bucket - A bucket must be completely empty before it can be
             * deleted, so remember to delete any objects from your buckets before
             * you try to delete them.
             */
            System.out.println("Deleting bucket " + bucketName + "\n");
            s3.deleteBucket(bucketName);
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    /**
     * Creates a temporary file with text data to demonstrate uploading a file
     * to Amazon S3
     *
     * @return A newly created temporary file with text data.
     *
     * @throws IOException
     */
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("01234567890112345678901234\n");
        writer.write("!@#$%^&*()-=[]{};':',.<>/?\n");
        writer.write("01234567890112345678901234\n");
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.close();

        return file;
    }

    /**
     * Displays the contents of the specified input stream as text.
     *
     * @param input
     *            The input stream to display as text.
     *
     * @throws IOException
     */
    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
    }
}
