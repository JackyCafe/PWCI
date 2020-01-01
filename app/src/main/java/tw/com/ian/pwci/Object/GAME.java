package tw.com.ian.pwci.Object;

import java.io.Serializable;

public class GAME implements Serializable {
    Long id;
    String topic;
    int status;
    String postword;
    int process;

    public GAME() {
    }

    public GAME(Long id, String topic, int status, String postword, int process) {
        this.id = id;
        this.topic = topic;
        this.status = status;
        this.postword = postword;
        this.process = process;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPostword() {
        return postword;
    }

    public void setPostword(String postword) {
        this.postword = postword;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "GAME{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", status=" + status +
                ", postword='" + postword + '\'' +
                ", process=" + process +
                '}';
    }
}
