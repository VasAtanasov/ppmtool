package org.tools.ppmtool;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.data.repositories.UserRepository;
import org.tools.ppmtool.service.models.UserServiceModel;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.service.services.UserService;
import org.tools.ppmtool.web.models.requests.ProjectCreateRequest;
import org.tools.ppmtool.web.models.requests.UserRegisterRequest;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DatabaseLoader implements CommandLineRunner {

  private final UserService userService;
  private final ProjectService projectService;
  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    UserRegisterRequest registerRequest =
        UserRegisterRequest.builder()
            .username("username@mail.com")
            .fullName("fullName")
            .password("123")
            .password("123")
            .build();

    UserServiceModel registeredUser = userService.register(registerRequest);

    User user =
        userRepository
            .findByUsername(registeredUser.getUsername())
            .orElseThrow(
                () -> new UsernameNotFoundException("User with given username was not found!"));

    ProjectCreateRequest Jetpulse =
        ProjectCreateRequest.builder()
            .projectName("Jetpulse")
            .description(
                "consequat dui nec nisi volutpat eleifend donec ut dolor morbi vel lectus in quam")
            .startDate(LocalDate.now())
            .build();
    ProjectCreateRequest Browsedrive =
        ProjectCreateRequest.builder()
            .projectName("Browsedrive")
            .description(
                "leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas")
            .startDate(LocalDate.now())
            .build();
    ProjectCreateRequest Brainverse =
        ProjectCreateRequest.builder()
            .projectName("Brainverse")
            .description(
                "volutpat erat quisque erat eros viverra eget congue eget semper rutrum nulla")
            .startDate(LocalDate.now())
            .build();
    ProjectCreateRequest Digitube =
        ProjectCreateRequest.builder()
            .projectName("Digitube")
            .description(
                "luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi sit amet")
            .startDate(LocalDate.now())
            .build();
    ProjectCreateRequest Zoonoodle =
        ProjectCreateRequest.builder()
            .projectName("Zoonoodle")
            .description(
                "ipsum aliquam non mauris morbi non lectus aliquam sit amet diam in magna bibendum")
            .startDate(LocalDate.now())
            .build();

    projectService.saveOrUpdateProject(Jetpulse, user);
    projectService.saveOrUpdateProject(Browsedrive, user);
    projectService.saveOrUpdateProject(Brainverse, user);
    projectService.saveOrUpdateProject(Digitube, user);
    projectService.saveOrUpdateProject(Zoonoodle, user);
  }
}
