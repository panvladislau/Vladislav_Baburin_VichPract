package code.domain;

public class User {
    private String username;
    private String password;

    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String name) {
        this. username = name;
    }

    public void setPassword(String password) {
        this. password = password;
    }

    public User(){
        username = "";
        password = "";
    }

    public User(String name, String password){
        this.username =  name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "username: ".concat(username).concat("\n")
                .concat("password: ").concat(password).concat("\n")
                .concat("\n");
    }
}