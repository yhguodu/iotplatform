package org.yhguodu.iot.auth.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.yhguodu.iot.auth.common.Role;

@Mapper
@Repository
public interface RoleMapper {
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "rolename", property = "rolename"),
    })
    Role getRoleById(@Param("id") int id);


    @Insert("insert into role(rolename)" +
            "values(#{rolename})"
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertRole(Role role);

    @Select("delete from role where id = #{id}")
    int deleteRole(@Param("id") int id);
}
