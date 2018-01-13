package com.dy.Invoicing.service;

import com.base.orm.service.BaseService;
import com.dy.Invoicing.domain.OrderModel;
import com.dy.Invoicing.repository.OrderDao;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDao;

public interface OrderService  extends BaseService<OrderDao, OrderModel, String> {

}
