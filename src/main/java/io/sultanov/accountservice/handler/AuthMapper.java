package io.sultanov.accountservice.handler;

import io.sultanov.accountservice.domain.user.UserDetailsImpl;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface AuthMapper {

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "lastname", column = "lastname"),
            @Result(property = "email", column = "email"),
            @Result(property = "userRole", column = "user_role")
    })
    @Select("""
            SELECT u.name, u.email, u.password, u.user_role
            FROM "user" as u
            WHERE u.email = '${email}';
            """)
    Optional<UserDetailsImpl> selectUserByEmail(@Param("email") String email);
}
