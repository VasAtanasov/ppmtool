package org.tools.ppmtool.data.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    @Column(name = "project_identifier", nullable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    @Column(name = "project_description", nullable = false)
    private String description;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    
    // @Column(name = "created_At")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;
    
    // @Column(name = "created_At")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

}