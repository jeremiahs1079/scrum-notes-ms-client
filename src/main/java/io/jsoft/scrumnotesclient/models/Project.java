package io.jsoft.scrumnotesclient.models;

import lombok.Data;

import java.util.Date;


@Data
public class Project {


    private long id;
    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
}
