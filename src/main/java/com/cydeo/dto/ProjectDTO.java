package com.cydeo.dto;


import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor // constructor with all the fields is used for project status page
public class ProjectDTO {

    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String projectDetail;
    private Status status;

    private int completeTaskCounts;
    private int unfinishedTaskCounts;

    public ProjectDTO(String projectName, String projectCode, UserDTO assignedManager, LocalDate startDate, LocalDate endDate, String projectDetail, Status status) { // this constructor is used in data Generator for create project page
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.assignedManager = assignedManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetail = projectDetail;
        this.status = status;
    }
}
