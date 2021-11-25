package com.example.demo.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import javax.persistence.*;

@ApiModel(value="File Doc",description="Model")
@Data
@Entity
@Table(name = "files")
public class File {
    @ApiModelProperty(value="File Id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ApiModelProperty(value="File Name")
    private String fileName;

    @ApiModelProperty(value="File Type")
    private String fileType;

    @ApiModelProperty(value="File Uri")
    private String url;

    public File() {
    }

    public File(String fileName, String fileType, String uri) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.url = uri;
    }
    public File(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

}

