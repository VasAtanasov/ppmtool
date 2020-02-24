package org.tools.ppmtool.exceptions;

import lombok.Data;

@Data
public class ProjectNotFoundExceptionResponse {
    private final String projectNotFound;
}
