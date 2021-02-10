package org.orlounge.common.util;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.orlounge.common.AppConstants;
import org.orlounge.exception.ORException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
public class ControllerUtils {

    private static final String[] IMAGES_EXT = {".jpg", ".gif", ".png", ".jpeg", ".tiff", ".bmp"};
    private static final String[] VALID_DOC_EXT = {".pdf"};
    private static final String[] INVALID_DOC_EXT = {".exe", ".sh", ".java", ".aspx", ".c", ".class"};


    /**
     * This method is used to get client ip address for login audit.
     */
    public static String getClientIpAddr(final HttpServletRequest request) {
        final String unknown = "unknown";
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }


    public static String getContextUrl(final HttpServletRequest request) {
        final StringBuffer url = new StringBuffer();
        url.append(request.getScheme()).append("://").append(request.getServerName()).append(':').append(request.getServerPort()).append(request.getContextPath()).append('/');
        return url.toString();
    }

    public static boolean isAjaxRequest(final HttpServletRequest request) {
        boolean isAjaxRequest;
        if (request == null) {
            isAjaxRequest = false;
        } else if (request.getHeader("X-Requested-With") == null) {
            isAjaxRequest = false;
        } else {
            isAjaxRequest = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        }
        return isAjaxRequest;
    }


    public static boolean isValidImageFile(String file) {
        String fileName = file.toLowerCase();
        return fileName.endsWith(".gif") || fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".bmp")
                || fileName.endsWith(".tiff");
    }

    public static boolean isValidExtension(MultipartFile file, String... ext) {
        String fileName = file.getOriginalFilename().toLowerCase();
        List<String> exts = Arrays.asList(ext);
        for (String e : exts) {
            if (fileName.endsWith(e)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidFileSize(MultipartFile file, int sizeInMB) {
        if (file != null && !file.isEmpty()) {
            if (file.getSize() > (sizeInMB * (1024 * 1024))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDocFile(MultipartFile file) {
        return isValidFileSize(file, 20) && !isValidExtension(file, INVALID_DOC_EXT);
    }

    public static boolean isValidImageOrDocFile(MultipartFile file) {
        List<String> allowedType = ListUtils.sum(Arrays.asList(IMAGES_EXT), Arrays.asList(VALID_DOC_EXT));
        ;
        return isValidFileSize(file, 20) && isValidExtension(file, allowedType.toArray(new String[0]));
    }

    public static boolean isValidDocFile(MultipartFile file, String[] supportingFormats) {
        return isValidFileSize(file, 20) && isValidExtension(file, supportingFormats);
    }

    public static ResponseEntity<byte[]> makeFileForView(final HttpServletResponse response, final File file, final String downloadFileName, final String fileType) {
        try {
            final HttpHeaders headers = new HttpHeaders();
            if (fileType.equals("pdf") || fileType.equalsIgnoreCase("application/pdf")) {
                headers.setContentType(MediaType.parseMediaType("application/pdf"));
            } else if (fileType.equals("html")) {
                headers.setContentType(MediaType.TEXT_HTML);
            } else if (fileType.equals("xls")) {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            } else if (fileType.equals("doc") || fileType.equals("docx")) {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            } else if (fileType.equals("png") || fileType.equals("jpg") || fileType.equals("bmp") || fileType.equals("jpeg") || fileType.equals("gif")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            } else {
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            }
            headers.add("content-disposition", "inline");
            //headers.setContentDispositionFormData(file.getName(), file.getName());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");


            ResponseEntity<byte[]> results = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
            file.deleteOnExit();
            return results;
        } catch (Exception exception) {
            throw new ORException(0);
        }
    }


    public static boolean downloadFile(final HttpServletResponse response, final File inputFile, final String downloadFileName, final String fileType) {

        try {
            final FileInputStream fileInput = new FileInputStream(inputFile);
            downloadFile(response, fileInput, downloadFileName, fileType);
            if (fileInput != null)
                fileInput.close();
            return true;
        } catch (Exception exception) {
            throw new ORException(0);
        }
    }

    public static void downloadFile(final HttpServletResponse response, final InputStream inputStream, final String downloadFileName, final String fileType) {
        try {
            if (fileType.equals("pdf")) {
                response.setContentType(AppConstants.PDF_FILE_CONTENT_TYPE);
            } else if (fileType.equals("html")) {
                response.setContentType(AppConstants.HTML_FILE_CONTENT_TYPE);
            } else if (fileType.equals("xls")) {
                response.setContentType(AppConstants.EXCEL_FILE_CONTENT_TYPE);
            } else if (fileType.equals("doc") || fileType.equals("docx")) {
                response.setContentType(AppConstants.DOC_FILE_CONTENT_TYPE);
            } else {
                response.setContentType(fileType);
            }

            response.setHeader("Content-disposition", "attachment; filename=" + downloadFileName);
            IOUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
            response.flushBuffer();
        } catch (Exception exception) {
            throw new ORException(0);
        }
    }

    public static String hashToHexString(byte[] byteData)
    {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            // NB! E.g.: Integer.toHexString(0x0C) will return "C", not "0C"
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }




}
