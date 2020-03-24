package com.yx.security;

import com.yx.bean.Admin;
import com.yx.bean.Permission;
import com.yx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/23 16:35
 */
@Configuration
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByUsername(username);
        List<Permission> permissions = adminService.getPermissionListByUsername(username);
        MyUser user = new MyUser();
        user.setPassword(admin.getPassword());
        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), permissions.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList()));
    }
}
