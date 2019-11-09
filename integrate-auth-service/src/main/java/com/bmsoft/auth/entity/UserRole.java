package com.bmsoft.auth.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRole {
    private Long id;

    private UUID userId;

    private UUID roleId;

    private Boolean isDefault = true;

    public static class UserRoleBuilder {

        private UserRole userRole = new UserRole();

        public UserRoleBuilder withUserId(UUID userId) {
            userRole.setUserId(userId);
            return this;
        }

        public UserRoleBuilder withRoleId(UUID roleId) {
            userRole.setRoleId(roleId);
            return this;
        }

        public UserRole build() {
            return userRole;
        }
    }
}
