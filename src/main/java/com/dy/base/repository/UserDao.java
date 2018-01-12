package com.dy.base.repository;

import com.base.orm.repository.BaseDao;
import com.dy.base.domain.UserModel;

public interface UserDao  extends BaseDao<UserModel, String>, UserDaoPlus  {

    public UserModel findByUsername(String username);

}
