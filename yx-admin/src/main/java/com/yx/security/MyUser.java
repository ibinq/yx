package com.yx.security;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/23 16:34
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;}
