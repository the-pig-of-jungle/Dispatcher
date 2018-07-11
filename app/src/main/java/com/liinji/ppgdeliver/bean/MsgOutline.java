package com.liinji.ppgdeliver.bean;

/**
 * Created by Administrator on 2017/4/26.
 */
public class MsgOutline {

    /**
     * MessageTypeName : 系统消息
     * MessageTypeValue : 0
     * UnReadCount : 1
     */

    private String MessageTypeName;
    private int MessageTypeValue;
    private int UnReadCount;

    public String getMessageTypeName() {
        return MessageTypeName;
    }

    public void setMessageTypeName(String MessageTypeName) {
        this.MessageTypeName = MessageTypeName;
    }

    public int getMessageTypeValue() {
        return MessageTypeValue;
    }

    public void setMessageTypeValue(int MessageTypeValue) {
        this.MessageTypeValue = MessageTypeValue;
    }

    public int getUnReadCount() {
        return UnReadCount;
    }

    public void setUnReadCount(int UnReadCount) {
        this.UnReadCount = UnReadCount;
    }
}
