package MovieReviewSystem.Users.repository;

import MovieReviewSystem.Users.User;

public interface UserRepository {

    public void save (User user);

    public User getByName (String userName);
}
