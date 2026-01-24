package br.com.kubo.authapi.service;

import br.com.kubo.authapi.model.User;
import br.com.kubo.authapi.security.MyToken;

public interface IUserService {
    public User addUser(User user);
    public User getByUsername(String username);
    public MyToken userLogin(User user);
}