package com.dy.base.service.impl;

import com.base.orm.service.impl.BaseServiceImpl;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDao;
import com.dy.base.service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userSerive")
public class UserSeriveImpl extends BaseServiceImpl<UserDao,UserModel, String> implements UserSerive {

    @Autowired
    public UserSeriveImpl(UserDao userDao) {
        super(userDao);
    }

}