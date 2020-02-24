package org.tools.ppmtool.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserNotFoundResponse {
    private String userNotFound;    
}