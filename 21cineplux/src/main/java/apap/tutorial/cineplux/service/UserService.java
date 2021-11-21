package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> retrieveListUser();
    void deleteUser(UserModel user);
    UserModel getUserById(String Id);
    String getUserRole(Authentication auth);
    Boolean comparePassword(String password,String encryptPassword);
    Boolean compareEmail(String email);
}
