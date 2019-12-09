package tw.com.ian.pwci.Object;

import java.io.Serializable;

public class Msg implements Serializable {
    private String msg;
    private TYPE type;

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
