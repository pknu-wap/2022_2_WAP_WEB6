package com.web.backend.user;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "AUTH_AUTHORITY")
@Entity
public class AuthorityEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_CODE")
    private String roleCode;

    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;



    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return roleCode;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getRoleCode() {
        return roleCode;
    }



    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }



    public String getRoleDescription() {
        return roleDescription;
    }



    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }



}