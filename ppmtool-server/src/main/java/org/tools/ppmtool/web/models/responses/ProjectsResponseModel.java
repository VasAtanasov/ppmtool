package org.tools.ppmtool.web.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectsResponseModel {

    private long total;
    private List<? extends ProjectResponseModel> posts;
}
