package tw.com.ian.pwci.Object;

import java.io.Serializable;

public class GameLevel implements Serializable {
    long id;
    int stage_id; //第幾關
    String Topic ; //題目
    int color;

    public GameLevel() {
    }

    public GameLevel(long id, int stage_id, String topic, int color) {
        this.id = id;
        this.stage_id = stage_id;
        Topic = topic;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStage_id() {
        return stage_id;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "GameLevel{" +
                "id=" + id +
                ", stage_id=" + stage_id +
                ", Topic='" + Topic + '\'' +
                ", color=" + color +
                '}';
    }
}
