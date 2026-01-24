package br.com.kubo.authapi.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kubo.authapi.model.User;
import br.com.kubo.authapi.repo.UserRepo;
import br.com.kubo.authapi.security.MyToken;
import br.com.kubo.authapi.security.TokenUtil;

@Service
public class UserServiceImpl implements IUserService{

    private UserRepo repo;


    public UserServiceImpl(UserRepo repo) {
        super();
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        // TODO Auto-generated method stub
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MyToken userLogin(User user) {
        // TODO Auto-generated method stub

        User storedUser = repo.findByUsername(user.getUsername()).orElseThrow(()->new RuntimeException("User not found!"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
            return TokenUtil.encode(storedUser);
        }
        throw new RuntimeException("Unauthorized user");
    }

}