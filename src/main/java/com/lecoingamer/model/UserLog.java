package com.lecoingamer.model;


import javax.persistence.*;
import java.util.Date;

/**
 * @author Ali Belemlih
 * Entity UserLog, with getter and setters. Represents the Logs of the user.
 * There is a Many to one relationship with entity @see User
 */

@Entity
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    private String IP;
    private Date logTime;
    private String comment;
    @ManyToOne
    private User user;

    public UserLog() {
    }

    public UserLog(String IP, Date logTime, User user) {
        this.IP = IP;
        this.logTime = logTime;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
