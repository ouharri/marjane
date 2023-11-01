package com.marjane.controllers;

import com.marjane.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

/**
 * This controller helps client check if its auth token is not expired yet
 *
 * @author Maksym Panov
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v2/ping")
@RequiredArgsConstructor
public class PingController {
    private final JwtService jwtService;
//    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> checkToken(
            @PathVariable("id") Integer id,
            @RequestBody String jwt
    ) {
        if (jwtService.isTokenExpired(jwt)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

//        User user = userService.getById(id);
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }

    @PostMapping("/manager/{id}")
    public ResponseEntity<Boolean> checkForManagerAuthority(
            @PathVariable("id") Integer id,
            @RequestBody String jwt
    ) {
        if (jwtService.isTokenExpired(jwt)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

//        User user = userService.getById(id);
//        Access a = user.getAccess();

        return new ResponseEntity<>(
                        true,
                HttpStatus.OK
        );
    }

    @PostMapping("/admin/{id}")
    public ResponseEntity<Boolean> checkForAdminAuthority(
            @PathVariable("id") Integer id,
            @RequestBody String jwt
    ) {
        if (jwtService.isTokenExpired(jwt)) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }
}
