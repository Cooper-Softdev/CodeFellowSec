package com.codefellowsec.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dateOfBirth;
    private String bio;

    @OneToMany(mappedBy = "applicationUser")
    private List<DoPost> posts;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "followee_id")}
    )

    private Set<ApplicationUser> usersIFollow = new HashSet<>();

    @ManyToMany(mappedBy = "usersIFollow")
    private Set<ApplicationUser> usersWhoFollowMe = new HashSet<>();

    public ApplicationUser() {}

    public ApplicationUser(String username, String firstName, String lastName, LocalDate dateOfBirth, String bio) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public void setUsername(String username) { this.username = username; }
    public String getUsername() { return this.username; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFirstName() { return this.firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getLastName() { return this.lastName; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword() { return this.password; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public LocalDate getDateOfBirth() { return this.dateOfBirth; }

    public void setBio(String bio) { this.bio = bio; }
    public String getBio() { return this.bio; }

    public List<DoPost> getPosts() { return this.posts; }

    public Set<ApplicationUser> getUsersIFollow() { return usersIFollow; }
    public void setUsersIFollow(Set<ApplicationUser> usersIFollow) { this.usersIFollow = usersIFollow; }

    public Set<ApplicationUser> getUsersWhoFollowMe() { return usersWhoFollowMe; }
    public void setUsersWhoFollowMe(Set<ApplicationUser> usersWhoFollowMe) { this.usersWhoFollowMe = usersWhoFollowMe; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bio='" + bio + '\'' +
                '}';
    }
}
