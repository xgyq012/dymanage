package com.dy.base.service;

import com.base.orm.service.BaseService;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDao;

public interface UserSerive  extends BaseService<UserDao, UserModel, String> {

}
