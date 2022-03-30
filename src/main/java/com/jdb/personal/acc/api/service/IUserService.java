package com.jdb.personal.acc.api.service;

import com.jdb.personal.acc.api.entity.User;
import com.jdb.personal.acc.api.exception.NotUserException;

public interface IUserService {

    User findByEmail(String email) throws NotUserException;
}
