package com.qianma.community.common;

import com.qianma.community.Model.User;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/4/18
 */
@Data
public class DataEntity implements Serializable {
    private String id;
    private Date gmtCreate;
    private Date gmtModified;
    private User creator;
    private User updator;
    public String getId() {
        if (null==id ||id.isEmpty()){
            this.id = UUID.randomUUID().toString();
        }
        return id;
    }

    public Date getGmtCreate() {
        if (this.gmtCreate == null){
            this.gmtCreate = new Date();
        }
        return gmtCreate;
    }

    public Date getGmtModified() {
        if (this.gmtModified == null){
            this.gmtModified = new Date();
        }
        return gmtModified;
    }
}
