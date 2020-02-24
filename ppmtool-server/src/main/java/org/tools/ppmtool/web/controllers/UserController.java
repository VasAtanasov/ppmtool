package org.tools.ppmtool.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.auth.JwtTokenProvider;
import org.tools.ppmtool.service.models.UserServiceModel;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.UserService;
import org.tools.ppmtool.web.models.requests.LoginRequest;
import org.tools.ppmtool.web.models.requests.UserRegisterRequest;
import org.tools.ppmtool.web.models.responses.JwtLoginSuccessResponse;

import lombok.RequiredArgsConstructor;

import static org.tools.ppmtool.constants.SecurityConstants.TOKEN_PREFIX;;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class UserController {

    private final UserService userService;
    private final MapValidationErrorService mapValidationErrorService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        JwtLoginSuccessResponse token = JwtLoginSuccessResponse.builder().success(true).token(jwt).build();
        return ResponseEntity.ok(token);
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest registerRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        UserServiceModel registeredUser = userService.register(registerRequest);

        return new ResponseEntity<UserServiceModel>(registeredUser, HttpStatus.CREATED);
    }

}