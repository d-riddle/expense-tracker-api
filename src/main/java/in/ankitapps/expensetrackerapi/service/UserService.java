package in.ankitapps.expensetrackerapi.service;

import in.ankitapps.expensetrackerapi.entity.User;
import in.ankitapps.expensetrackerapi.entity.UserModel;

public interface UserService {
    User createUser(UserModel user);

    User readUser();

    User updateUser(UserModel user);

    void deleteUser();

    User getLoggedInUser();
}
