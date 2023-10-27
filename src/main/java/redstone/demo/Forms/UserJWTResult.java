package redstone.demo.Forms;

import redstone.demo.models.User;

public class UserJWTResult {
    public String username;
    public String email;
    public String firsName;
    public String lastName;
    public String role;
    public String token;

    public UserJWTResult(User user, String token) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firsName = user.getFirsName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.token = token;
    }
}
