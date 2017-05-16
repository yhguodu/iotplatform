package org.yhguodu.iot.auth.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.yhguodu.iot.auth.common.Permission;
import org.yhguodu.iot.auth.common.Role;

import java.util.List;

@Mapper
@Repository
public interface AuthMapper {

    @Select("select  role.id,role.rolename"+
            " from role,user_role"+
            " where role.id=user_role.user_id " +
            "and user_role.uid = #{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "rolename", property = "rolename")
    })
    List<Role> getRoleNamesForUser(int uid);

    @Select("select  permission.id,permission.permissionname,permission.role_id"+
            " from permission"+
            " where permission.role_id=#{roleId} ")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "permissionname", property = "permissionName"),
            @Result(column = "role_id", property = "roleId")
    })
    List<Permission> getPermissions(int roleId);
}
