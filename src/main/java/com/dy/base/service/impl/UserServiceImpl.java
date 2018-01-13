package com.dy.base.service.impl;

import com.base.orm.service.impl.BaseServiceImpl;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDao;
import com.dy.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserDao,UserModel, String> implements UserService {

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        super(userDao);
    }

}