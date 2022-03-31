package com.jdb.personal.acc.api.service.impl;

import com.jdb.personal.acc.api.entity.AppUser;
import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.repository.IUserRepository;
import com.jdb.personal.acc.api.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public AppUser findByEmail(String email) throws NotUserException {
        AppUser resp = userRepository.findUserByEmail(email.toLowerCase(Locale.ROOT));
        validateUser(resp);
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(Long id) throws NotUserException {
        AppUser resp = userRepository.fetchById(id);
        validateUser(resp);
        return resp;
    }

    @Override
    @Transactional(readOnly = true)
    public AppUser clearPassword(AppUser user) {
        user.setPassword("NO-PASSWORD");
        return user;
    }

    private void validateUser(AppUser user) throws NotUserException{
        if (user == null || user.getId() == null || user.getId().equals(0L)) throw new NotUserException("El usuario no existe");
    }
}