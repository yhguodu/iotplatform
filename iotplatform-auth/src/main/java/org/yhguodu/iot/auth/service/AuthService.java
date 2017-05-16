package org.yhguodu.iot.auth.service;

import org.springframework.stereotype.Service;
import org.yhguodu.iot.auth.common.Permission;
import org.yhguodu.iot.auth.common.Role;
import org.yhguodu.iot.auth.common.User;

import java.util.List;
import java.util.Set;

@Service
public interface AuthService {

    User getUserById(int id);

    User getUserByName(String name);

    Set<String> getRoleNames(int uid);

    List<Role> getRoles(int id);

    List<Permission> getPermissions(int roleId);

    Set<String> getPermissionNames(int roleId);
}
