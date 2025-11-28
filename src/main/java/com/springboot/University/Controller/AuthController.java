package com.springboot.University.Controller;

import com.springboot.University.DTO.LoginDetails;
import com.springboot.University.DTO.LoginResponse;
import com.springboot.University.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

//    This endpoint is used to generate Jwt token
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetails){

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDetails.getUsername(),loginDetails.getPassword());
        authenticationManager.authenticate(authToken);
        String role = loginDetails.getRole();

        String token = jwtUtil.generateToken(loginDetails.getUsername(), role);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
