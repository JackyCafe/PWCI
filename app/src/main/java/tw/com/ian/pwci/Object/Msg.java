package tw.com.ian.pwci.Object;

import java.io.Serializable;


/**
 *  聊天室對話類別
 *
 * */
public class Msg implements Serializable {
    private String msg;  //訊息文字
    private TYPE type;  // 訊息模式，SENT / RECEIVED

    public enum TYPE{
        RECEIVED,
        SENT
    }


    public Msg(String msg, TYPE type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public TYPE getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                ", type=" + type +
                '}';
    }
}
