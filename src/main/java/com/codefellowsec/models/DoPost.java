package com.codefellowsec.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DoPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private ApplicationUser applicationUser;

    private String body;
    private LocalDateTime time;

    public DoPost() {}

    public DoPost(String body, LocalDateTime time, ApplicationUser applicationUser) {
        this.body = body;
        this.time = time;
        this.applicationUser = applicationUser;
    }

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public ApplicationUser getApplicationUser() { return this.applicationUser; }
    public void setApplicationUser(ApplicationUser applicationUser) { this.applicationUser = applicationUser; }

    public String getBody() { return this.body; }
    public void setBody(String body) { this.body = body; }

    public LocalDateTime getTime() { return this.time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    @Override
    public String toString() {
        return "DoPost{" +
                "id=" + id +
                ", applicationUser=" + applicationUser +
                ", body='" + body + '\'' +
                ", time=" + time +
                '}';
    }
}
