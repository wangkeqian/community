package com.qianma.community.myenum;

import lombok.Data;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/5/2
 */

public enum CommentType {
    FIRSTLEVEL("一级评论","FIRSTLEVEL"),
    SECONDLEVEL("二级评论","SECONDLEVEL");

    private String desc;
    private String value;

    CommentType() {
    }

    CommentType(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static CommentType getEnum(String value){
        CommentType resultEnum=null;
        CommentType[] enumAry=CommentType.values();
        for(int i=0;i<enumAry.length;i++){
            if(enumAry[i].getValue()==value){
                resultEnum=enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

}
