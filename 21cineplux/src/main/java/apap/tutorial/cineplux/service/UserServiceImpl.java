package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.UserModel;
import apap.tutorial.cineplux.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDB;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDB.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public Boolean comparePassword(String password, String encryptPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean compare = passwordEncoder.matches(password, encryptPassword);
        return compare;
    }



    @Override
    public List<UserModel> retrieveListUser(){
        return userDB.findAll();
    }

    @Override
    public void deleteUser(UserModel user) {
        userDB.delete(user);
    }

    @Override
    public UserModel getUserById(String Id) {
        Optional<UserModel> user = userDB.findById(Id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    @Override
    public String getUserRole(Authentication auth){
        String role = "";
        if (auth.getAuthorities().toString().equals("[ADMIN]")) {
            role = "ADMIN";
        } else if(auth.getAuthorities().toString().equals("[MANAGER]")){
            role = "MANAGER";
        } else if(auth.getAuthorities().toString().equals("[USER]")){
            role = "USER";
        }
        return role;
    }

}
