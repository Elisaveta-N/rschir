package com.example.prac7auth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Data
@RedisHash
public class User {

    @Id
    @ToString.Include
    private String id;

    @NotNull
    @Size(min = 2, max = 48)
    @ToString.Include
    @Indexed
    private String name;

    @NotNull
    @Email
    @EqualsAndHashCode.Include
    @ToString.Include
    @Indexed
    private String email;

    @NotNull
    private String password;

    //@Transient
    //private String passwordConfirm;

    @ToString.Include
    @Indexed
    private String jwt;

    @Reference
    private Set<Role> roles = new HashSet<Role>();

    public User addRole(Role role) {
        roles.add(role);
        return this;
    }
}
