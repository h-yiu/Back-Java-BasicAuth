package io.hyao.userreglogindemo.repository;

import io.hyao.userreglogindemo.entity.User;
public interface UserRepository {
    String registerNewUser(User user);

    User getUserInfoByUserName(String userName);

}
