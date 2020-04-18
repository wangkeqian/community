package com.qianma.community.common;

import com.qianma.community.Model.User;
import lombok.Data;

import java.io.Serializable;
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
    private Long gmtCreate;
    private Long gmtModified;
    private User creator;
    private User updator;


    public String getId() {
        if (null==id ||id.isEmpty()){
            this.id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
