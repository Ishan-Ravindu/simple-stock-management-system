package auth;

public class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public boolean signIn() {
        UserList userList = new UserList();
        for (User i : userList.getUsers()) {
            if(this.email.equalsIgnoreCase(i.getEmail()) &&this.password.equalsIgnoreCase(i.getPassword())){
                return true;
            }
           }
        return false;
    }
    
}
