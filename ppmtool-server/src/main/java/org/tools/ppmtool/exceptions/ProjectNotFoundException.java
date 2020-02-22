package org.tools.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7111787899317886837L;

	public ProjectNotFoundException(String message) {
        super(message);
    }
}
