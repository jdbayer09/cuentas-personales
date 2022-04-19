package com.jdb.personal.acc.api.controller;

import com.jdb.personal.acc.api.entity.Deuda;
import com.jdb.personal.acc.api.service.IDeudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deuda")
public class DeudaController {

    @Autowired
    private IDeudaService deudaService;

    @GetMapping("/list-no-pagadas/{userId}")
    @Secured({ "ROLE_USER" })
    public ResponseEntity<?> listNoPagadas(@PathVariable(name = "userId") Long userId,
                                           @RequestParam boolean oculto) {
        try {
            List<Deuda> resp = deudaService.fetchNoPagadasByUserId(userId, oculto);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list-pagadas/{userId}")
    @Secured({ "ROLE_USER" })
    public ResponseEntity<?> listPagadas(@PathVariable(name = "userId") Long userId,
                                         @RequestParam boolean oculto) {
        try {
            List<Deuda> resp = deudaService.fetchPagadasByUserId(userId, oculto);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list/{userId}")
    @Secured({ "ROLE_USER" })
    public ResponseEntity<?> listAll(@PathVariable(name = "userId") Long userId,
                                     @RequestParam boolean oculto) {
        try {
            List<Deuda> resp = deudaService.fetchAllByUserId(userId, oculto);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
