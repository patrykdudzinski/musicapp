package dudzinski.musiconator;

public class User {
    // fields
    private int userID;
    private String email;
    private String password;
    // constructors
    public User() {}
    public User(int id, String email, String password) {
        this.userID = id;
        this.email = email;
        this.password = password;
    }
    // properties
    public void setID(int id) {
        this.userID = id;
    }
    public int getID() {
        return this.userID;
    }
    public void setEmail(String email) {
       this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
}