package org.yhguodu.iot.auth.common;

public class Permission {

    private int id;
    private String permissionName;
    private int roleId;

    public Permission(int id, String permissionName, int roleId) {
        this.id = id;
        this.permissionName = permissionName;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
