package ru.potapov.socketapp.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.potapov.socketapp.dao.UserDAO;
import ru.potapov.socketapp.models.User;

import java.util.Optional;

public class AuthService {
    public static User auth(String login, String password) {
        Optional<User> userOptional = new UserDAO().findUserByLogin(login);

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userOptional.get();
        System.out.println("USERPASS"+user.getLogin());

        System.out.println("USERPASS"+user.getPassword());
        if (encoder.matches(password, user.getPassword())) {
            System.out.println("I return user");
            return user;
        }
        System.out.println("REURN NULL");
        return null;
    }
}
