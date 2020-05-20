package my.learning.jdbc.bean;

@Deprecated
public class AccountUser extends Account {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return super.toString() + "AccountUser{" +
                "username='" + username + '\'' +
                '}';
    }
}
