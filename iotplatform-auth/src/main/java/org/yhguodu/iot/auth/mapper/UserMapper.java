package org.yhguodu.iot.auth.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.yhguodu.iot.auth.common.User;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "mail", property = "mail"),
    })
    User getUserById(@Param("id") int id);

    @Select("select * from user where username = #{name}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password"),
            @Result(column = "mail", property = "mail"),
    })
    User getUserByName(@Param("name") String name);

    @Insert("insert into user(username, password, mail)" +
            "values(#{name}, #{password}, #{mail})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    @Select("delete from user where id = #{id}")
    int deleteUser(@Param("id") int id);
}
