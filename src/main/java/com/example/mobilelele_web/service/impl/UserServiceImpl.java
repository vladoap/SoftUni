package com.example.mobilelele_web.service.impl;

import com.example.mobilelele_web.users.CurrentUser;
import com.example.mobilelele_web.model.entity.User;
import com.example.mobilelele_web.model.service.UserLoginServiceDto;
import com.example.mobilelele_web.repository.UserRepository;
import com.example.mobilelele_web.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean login(UserLoginServiceDto userLoginServiceDto) {

        Optional<User> userOpt = userRepository.findByUsername(userLoginServiceDto.getUsername());

        if (userOpt.isEmpty()) {
            logout();
            return false;
        } else {

          boolean successfulLogin = passwordEncoder.matches(userLoginServiceDto.getRawPassword(), userOpt.get().getPassword());
          
          if (successfulLogin) {
              User loggedInUser = userOpt.get();

              currentUser
                      .setLoggedIn(true)
                      .setUsername(loggedInUser.getUsername())
                      .setFirstName(loggedInUser.getFirstName())
                      .setLastName(loggedInUser.getLastName());




              loggedInUser.getRoles().forEach(r -> currentUser.addRole(r.getRole()));

          }

          return successfulLogin;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }


}
