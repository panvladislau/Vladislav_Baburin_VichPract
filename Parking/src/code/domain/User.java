package code.domain;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() { return isAdmin;}

    public void setUsername(String name) {
        this. username = name;
    }

    public void setPassword(String password) {
        this. password = password;
    }

    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin;}

    public User(){
        username = "";
        password = "";
    }

    public User(String name, String password, boolean isAdmin){
        this.username =  name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "username: ".concat(username).concat("\n")
                .concat("password: ").concat(password).concat("\n")
                .concat("\n");
    }

    public boolean equals(User user) {
        if (this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword()))
            return true;
        return false;
    }
}