package org.tools.ppmtool.service.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.tools.ppmtool.service.models.UserServiceModel;
import org.tools.ppmtool.web.models.requests.UserRegisterRequest;

public interface UserService extends UserDetailsService {

    UserServiceModel register(UserRegisterRequest UserRegisterRequest);

    Boolean existsByUsername(String username);
}