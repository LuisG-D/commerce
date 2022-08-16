package com.commerce.commerce.entity;

import com.commerce.commerce.chatMessage.ChatMessage;
import com.commerce.commerce.role.AppUserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@ToString

public class AppUser implements UserDetails {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private Long id;
    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;


    @OneToMany(
            mappedBy = "emisor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@JsonManagedReference
    private List<ChatMessage> chatMessageList = new ArrayList<>();
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String username,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;


    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public void addChatMessage(ChatMessage chatMessage) {
        this.chatMessageList.add(chatMessage);
    }

    public void addChatMessage(List<ChatMessage> chatMessageList) {
        this.chatMessageList.addAll(chatMessageList);
    }

}
