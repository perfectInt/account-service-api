package io.sultanov.accountservice.handler;

import io.sultanov.accountservice.domain.user.ChangePasswordRequest;
import io.sultanov.accountservice.domain.user.User;
import io.sultanov.accountservice.domain.user.UserDetailsImpl;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "lastname", column = "lastname"),
            @Result(property = "email", column = "email"),
            @Result(property = "userRole", column = "user_role")
    })
    @Select("""
            SELECT u.id, u.name, u.lastname, u.email, u.user_role
            FROM "user" as u
            WHERE u.id = '${id}';
            """)
    User selectUser(@Param("id") Long id);


    @Result(property = "id", column = "id")
    @Select("""
            INSERT INTO "user" (name, lastname, email, password, user_role)
            VALUES (#{name}, #{lastname}, #{email}, #{password}, #{userRole})
            RETURNING id;
            """)
    Long insertUser(User user) throws RuntimeException;

    @Update("""
            UPDATE "user"
            SET password = '${request.newPassword}'
            WHERE email = '${email}';
            """)
    void updatePassword(@Param("email") String email, ChangePasswordRequest request);
}
