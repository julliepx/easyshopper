package com.extia.easyshopper.stubs;

import com.extia.easyshopper.domain.model.User;

public interface IUserStub {
    static User buildUser() {
        return User.builder()
                .id("uuid")
                .name("User")
                .email("existent@email.com")
                .password("12345678")
                .build();
    }
}
