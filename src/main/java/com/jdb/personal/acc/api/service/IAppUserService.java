package com.jdb.personal.acc.api.service;

import com.jdb.personal.acc.api.entity.AppUser;
import com.jdb.personal.acc.api.exception.NotUserException;

public interface IAppUserService {

    AppUser findByEmail(String email) throws NotUserException;

    AppUser findById(Long id) throws NotUserException;

    AppUser clearPassword(AppUser user);
}
