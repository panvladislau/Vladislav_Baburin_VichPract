package code.dao;


public class UserEntity {
    private String username;
    private String password;
    private boolean isAdmin;

    public UserEntity(){
        this.username = null;
        this.password = null;
        this.isAdmin = false;
    }

    public UserEntity(String username, String password, boolean isAdmin){
        this.username = username;
        this.isAdmin = isAdmin;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password; }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) { this.password = password;}

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"username\" : \"").append(username).append("\",\n");
        sb.append("\"password\" : \"").append(password).append("\",\n");
        sb.append("\"isAdmin\" : ").append(isAdmin);
        sb.append('\n');
        return sb.toString();
    }
}