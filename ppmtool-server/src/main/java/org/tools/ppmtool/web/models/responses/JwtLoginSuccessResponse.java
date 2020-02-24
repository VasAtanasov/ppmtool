package org.tools.ppmtool.web.models.responses;

import lombok.Data;
import lombok.Builder;


@Data
@Builder
public class JwtLoginSuccessResponse {
    private boolean success;
    private String token;   
}