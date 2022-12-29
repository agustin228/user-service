package org.binar.userservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import org.binar.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users_movie")
public class UserMovieController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/new_user")
    @Operation(summary = "Menambahkan user baru")
    public ResponseEntity insertUser(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("username") String username) {
        Map<String, String> resp = new HashMap<>();

        try {
            userService.addNewUser(email, password, username);
            resp.put("message", "user baru dengan username " + username + " berhasil ditambahkan");
            return new ResponseEntity(resp, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("ERROR has been found! because : {}", e.getMessage());
            resp.put("message", "user gagal ditambahkan karena " + e.getMessage());
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PreAuthorize(value = "hasAuthority('CUSTOMER')")
    @PutMapping("/update")
    @Operation(summary = "Memperbarui data user")
    public ResponseEntity updateUser(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("username") String username) {

        Map<String, String> resp = new HashMap<>();

        try {
            userService.updateUser(email, password, username);

            resp.put("message", "data user dengan username " + username + " berhasil diperbaharui");
            return new ResponseEntity(resp, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR has been found! because : {}", e.getMessage());
            resp.put("message", "data user gagal diupdate dikarenakan " + e.getMessage());
            return new ResponseEntity(resp, HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize(value = "hasAuthority('CUSTOMER')")
    @DeleteMapping("/delete")
    @Operation(summary = "Menghapus data user")
    public ResponseEntity deleteUser(@RequestParam("username") String username) {
        Map<String, String> resp = new HashMap<>();
        try {
            userService.deleteUser(username);
            resp.put("message", "data user dengan username" + username + " berhasil dihapus");
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e) {
            log.error("ERROR has been found! because : {}", e.getMessage());
            resp.put("message", "data user gagal dihapus dikarenakan " + e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }


}
