package com.ninja.hrm.PojoClassUtility;

public class ProjectPojoClass {

    private String projectName;
    private String createdBy;
    private String status;
    private int teamsize;

    // ✅ Default constructor
    public ProjectPojoClass() {
    }

    // ✅ Parameterized constructor
    public ProjectPojoClass(String projectName, String createdBy, String status, int teamsize) {
        this.projectName = projectName;
        this.createdBy = createdBy;
        this.status = status;
        this.teamsize = teamsize;
    }

    // ✅ Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTeamsize() {
        return teamsize;
    }

    public void setTeamsize(int teamsize) {
        this.teamsize = teamsize;
    }

  
}
