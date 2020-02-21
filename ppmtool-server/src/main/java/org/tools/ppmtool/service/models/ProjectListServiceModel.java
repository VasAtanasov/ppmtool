package org.tools.ppmtool.service.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectListServiceModel {

    private long total;
    private List<? extends ProjectServiceModel> posts;
}
