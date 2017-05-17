package org.yhguodu.iot.auth.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhguodu.iot.auth.common.Permission;
import org.yhguodu.iot.auth.common.Role;
import org.yhguodu.iot.auth.common.User;
import org.yhguodu.iot.auth.mapper.AuthMapper;
import org.yhguodu.iot.auth.mapper.UserMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BaseAuthService implements  AuthService {
    private static  final Logger logger = LoggerFactory.getLogger(BaseAuthService.class.getName());
    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    public Set<String> getRoleNames(int uid) {
        List<Role> roles = authMapper.getRoleNamesForUser(uid);
        Set<String> result = new HashSet<String>();
        for(Role role: roles) {
            result.add(role.getRolename());
        }
        return result;
    }

    public List<Role> getRoles(int id) {
        return authMapper.getRoleNamesForUser(id);
    }

    public List<Permission> getPermissions(int roleId) {
        return authMapper.getPermissions(roleId);
    }

    public Set<String> getPermissionNames(int roleId) {
        List<Permission> permissions = authMapper.getPermissions(roleId);
        Set<String> result = new HashSet<String>();
        for(Permission permission :permissions) {
            logger.info("permission name:" + permission.getPermissionName());
            result.add(permission.getPermissionName());
        }
        return result;
    }
}
