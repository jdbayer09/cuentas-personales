package com.jdb.personal.acc.api.controller;

import com.jdb.personal.acc.api.entity.AppUser;
import com.jdb.personal.acc.api.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @GetMapping
    @Secured({ "ROLE_USER" })
    public ResponseEntity<?> listAll(Authentication auth) {
        try {
            AppUser resp = appUserService.findByEmail(auth.getName());
            resp.setPassword(null);
            resp.setCategories(null);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody AppUser user) {
        try {
            return new ResponseEntity<>(appUserService.createUser(user), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
