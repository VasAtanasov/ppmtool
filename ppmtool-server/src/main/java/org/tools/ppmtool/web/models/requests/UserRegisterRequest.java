package org.tools.ppmtool.web.models.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.tools.ppmtool.web.models.validations.annotations.MatchingFieldsConstraint;

@Data
@Builder
@MatchingFieldsConstraint(fields = {"password", "confirmPassword"})
public class UserRegisterRequest {
  @Email(message = "Username needs to be an email")
  @NotBlank(message = "username is required")
  private String username;

  @NotBlank(message = "Please enter your full name")
  private String fullName;

  @NotBlank(message = "Password field is required")
  private String password;

  private String confirmPassword;
}
