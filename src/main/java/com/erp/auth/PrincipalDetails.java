package com.erp.auth;

import com.erp.dto.ManagerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final ManagerDTO manager;

    public ManagerDTO getManager(){ return manager; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> manager.getRole());
    }

    @Override
    public String getPassword() {
        return manager.getPw();
    }

    @Override
    public String getUsername() {
        return manager.getManagerId(); // 로그인 ID
    }



}
