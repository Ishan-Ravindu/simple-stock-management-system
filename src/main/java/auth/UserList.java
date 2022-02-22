package auth;

public class UserList {
    private User[] users = { new User("group11", "group11@gmail.com", "123@group11"),
            new User("group", "group@gmail.com", "123@group") };

    public User[] getUsers() {
        return users;
    }

}
