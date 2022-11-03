package playlist.myplaylist.controller;

public class MemberForm {

    @Override
    public String toString() {
        return "MemberForm{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    String id;
    String password;
    String name;

    // SETTER 어디서 쓰임?
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
