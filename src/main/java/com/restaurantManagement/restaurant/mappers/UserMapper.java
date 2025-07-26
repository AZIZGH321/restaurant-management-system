package com.restaurantManagement.restaurant.mappers;


import com.restaurantManagement.restaurant.dtos.signup.UserResponseDTO;
import com.restaurantManagement.restaurant.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "role.name",target = "role")
    UserResponseDTO userToUserResponseDTO(User user);

}
