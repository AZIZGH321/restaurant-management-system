package com.restaurantManagement.restaurant.dtos.signup;


import com.restaurantManagement.restaurant.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;

    private String email;

    private String fullName;

    private Date createdAt;

    private Date updatedAt;

    private RoleEnum role;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean CredentialsNonExpired;

    private Boolean accountNonLocked;
}
