package com.hellochemo.controller;

public class tictactoeOutput {
    private int id;
    private String uname;
    private String upass;
    private Boolean status;
    private Integer Session;
    private Integer points;
    private Integer highScore;

    public tictactoeOutput(int id, String uname, String upass, Boolean status, Integer session, Integer points, Integer highScore) {
        this.id = id;
        this.uname = uname;
        this.upass = upass;
        this.status = status;
        Session = session;
        this.points = points;
        this.highScore = highScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSession() {
        return Session;
    }

    public void setSession(Integer session) {
        Session = session;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }
}
