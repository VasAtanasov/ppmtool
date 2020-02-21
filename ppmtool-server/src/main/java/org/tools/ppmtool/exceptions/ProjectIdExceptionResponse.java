package org.tools.ppmtool.exceptions;

public class ProjectIdExceptionResponse {

    private String id;

    public ProjectIdExceptionResponse(String id) {
        this.id = id;
    }

    public String getProjectIdentifier() {
        return id;
    }

    public void setProjectIdentifier(String id) {
        this.id = id;
    }
}
