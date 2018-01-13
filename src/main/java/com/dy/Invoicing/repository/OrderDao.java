package com.dy.Invoicing.repository;

import com.base.orm.repository.BaseDao;
import com.dy.Invoicing.domain.OrderModel;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDaoPlus;

public interface OrderDao extends BaseDao<OrderModel, String>, OrderDaoPlus {

}
