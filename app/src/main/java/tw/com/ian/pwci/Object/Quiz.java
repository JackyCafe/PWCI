package tw.com.ian.pwci.Object;


import java.io.Serializable;

public class Quiz implements Serializable {
    Long id ;
    String question;

    public Quiz() {}

    public Quiz(Long id, String question) {
            this.id = id;
            this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
