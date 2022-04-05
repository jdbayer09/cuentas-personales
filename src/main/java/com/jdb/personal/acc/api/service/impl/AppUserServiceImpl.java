package com.jdb.personal.acc.api.service.impl;

import com.jdb.personal.acc.api.entity.AppUser;
import com.jdb.personal.acc.api.exception.NotUserException;
import com.jdb.personal.acc.api.repository.IUserRepository;
import com.jdb.personal.acc.api.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AppUserServiceImpl implements IAppUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

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
    @Transactional
    public AppUser createUser(AppUser user) throws NotUserException {
        AppUser validateEmail = userRepository.findUserByEmail(user.getEmail());
        if (validateEmail != null ) throw  new NotUserException("El email ya se encuentra registrado");
        user.setEmail(user.getEmail().toLowerCase());
        user.setFirstName(user.getFirstName().toUpperCase());
        user.setLastName(user.getLastName().toUpperCase());
        if (user.getSecondName() != null)
            user.setSecondName(user.getSecondName().toUpperCase());
        user.setPassword(passEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void validateUser(AppUser user) throws NotUserException{
        if (user == null || user.getId() == null || user.getId().equals(0L)) throw new NotUserException("El usuario no existe");
    }
}
