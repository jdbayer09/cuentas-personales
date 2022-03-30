package com.jdb.personal.acc.api.service.impl;

import com.jdb.personal.acc.api.entity.User;
import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.repository.IUserRepository;
import com.jdb.personal.acc.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public User findByEmail(String email) throws NotUserException {
        User resp = userRepository.findUserByEmail(email.toLowerCase(Locale.ROOT));
        if (resp == null || resp.getId() == null || resp.getId().equals(0L)) throw new NotUserException("El usuario no existe");
        return resp;
    }
}
