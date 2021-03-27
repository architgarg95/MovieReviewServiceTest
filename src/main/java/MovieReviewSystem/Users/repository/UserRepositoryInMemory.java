package MovieReviewSystem.Users.repository;

import MovieReviewSystem.Users.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {

    private static Map<String, User> userStore;

    public UserRepositoryInMemory() {
        userStore = new HashMap<>();
    }

    public void save (User user) {
        userStore.put(user.getName(), user);
    }

    public User getByName (String userName) {
        return userStore.getOrDefault(userName, null);
    }
}
