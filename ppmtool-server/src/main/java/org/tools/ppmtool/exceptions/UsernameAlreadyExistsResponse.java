package org.tools.ppmtool.exceptions;

import lombok.Data;

@Data
public class UsernameAlreadyExistsResponse {
    private final String username;
}
