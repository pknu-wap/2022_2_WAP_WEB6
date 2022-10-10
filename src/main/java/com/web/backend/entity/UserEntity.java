package com.web.backend.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "AUTH_USER_DETAILS")
@Entity
@Data
public class UserEntity implements UserDetails { //UserDetails 는 Spring Security  에서 사용자의 정보를 담는 인터페이스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "USER_KEY")
    private String password;


    @Column(name = "CREATED_ON")
    private Date createdAt;

    @Column(name = "UPDATED_ON")
    private Date updatedAt;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "enabled")
    private boolean enabled=true;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "AUTH_USER_AUTHORITY", joinColumns = @JoinColumn(referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(referencedColumnName ="id"))
    private List<AuthorityEntity> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //계정의 권한 목록을 리턴

        return authorities;
    }

    @Override
    public String getPassword() { // 계정의 비밀번호를 리턴
        return this.password;
    }

    @Override
    public String getUsername() { // 계정의 고유한 값을 리턴
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정의 만료 여부 리턴	true ( 만료 안됨 )
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정의 잠김 여부 리턴	true ( 잠기지 않음 )
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 만료 여부 리턴	true ( 만료 안됨 )
        return this.enabled;
    }

    @Override
    public boolean isEnabled() { // 	계정의 활성화 여부 리턴	true ( 활성화 됨 )
        return this.enabled;
    }
}
