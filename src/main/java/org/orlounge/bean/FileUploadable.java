package org.orlounge.bean;

import javax.persistence.Column;

/**
 * Created by satyam on 4/24/2017.
 */
public abstract class FileUploadable {


    @Column(name = "file_name")
    private String name;


    @Column(name = "file_id")
    private String fileId;


    @Column(name =  "file_path")
    private String filePath;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
