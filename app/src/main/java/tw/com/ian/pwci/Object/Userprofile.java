package tw.com.ian.pwci.Object;


import java.io.Serializable;

public class Userprofile implements Serializable {
    Long id;
    String user;
    String card_id;
    String name;
    String birthday;
    String blood;
    String address;
    String tel;
    String mobile;
    String contact;
    String contact_tel;
    String contact_relation;
    String first_career;
    String last_career;
    String room;
    String isStaff;


    public Userprofile() {}
    public Userprofile(Long id, String user, String card_id, String name, String birthday, String blood, String address
            , String tel, String mobile, String contact, String contact_tel, String contact_relation
            , String first_career, String last_career, String room, String isStaff) {
        this.id = id;
        this.user = user;
        this.card_id = card_id;
        this.name = name;
        this.birthday = birthday;
        this.blood = blood;
        this.address = address;
        this.tel = tel;
        this.mobile = mobile;
        this.contact = contact;
        this.contact_tel = contact_tel;
        this.contact_relation = contact_relation;
        this.first_career = first_career;
        this.last_career = last_career;
        this.room = room;
        this.isStaff = isStaff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getContact_relation() {
        return contact_relation;
    }

    public void setContact_relation(String contact_relation) {
        this.contact_relation = contact_relation;
    }

    public String getFirst_career() {
        return first_career;
    }

    public void setFirst_career(String first_career) {
        this.first_career = first_career;
    }

    public String getLast_career() {
        return last_career;
    }

    public void setLast_career(String last_career) {
        this.last_career = last_career;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }

    @Override
    public String toString() {
        return "Userprofile{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", card_id='" + card_id + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", blood='" + blood + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", contact='" + contact + '\'' +
                ", contact_tel='" + contact_tel + '\'' +
                ", contact_relation='" + contact_relation + '\'' +
                ", first_career='" + first_career + '\'' +
                ", last_career='" + last_career + '\'' +
                ", room='" + room + '\'' +
                ", isStaff='" + isStaff + '\'' +
                '}';
    }
}
