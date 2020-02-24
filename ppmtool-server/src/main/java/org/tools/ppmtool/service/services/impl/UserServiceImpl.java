package org.tools.ppmtool.service.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.data.repositories.UserRepository;
import org.tools.ppmtool.exceptions.UsernameAlreadyExistsException;
import org.tools.ppmtool.service.models.UserServiceModel;
import org.tools.ppmtool.service.services.UserService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.UserRegisterRequest;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapperWrapper modelMapper;
  private final PasswordEncoder encoder;

  @Override
  public Boolean existsByUsername(String username) {
    return userRepository.existsByUsernameIgnoreCase(username);
  }

  @Override
  public UserServiceModel register(UserRegisterRequest registerRequest) {

    if (existsByUsername(registerRequest.getUsername())) {
      throw new UsernameAlreadyExistsException(
          "Username '" + registerRequest.getUsername() + "' already exists");
    }

    registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));

    User user = modelMapper.map(registerRequest, User.class);

    return modelMapper.map(userRepository.save(user), UserServiceModel.class);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("User with given username was not found!"));
  }

  @Override
  public UserDetails loadUserById(String id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User was not found!"));
  }
}
