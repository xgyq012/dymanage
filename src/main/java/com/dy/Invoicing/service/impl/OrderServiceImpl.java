package com.dy.Invoicing.service.impl;

import com.base.orm.service.impl.BaseServiceImpl;
import com.dy.Invoicing.domain.OrderModel;
import com.dy.Invoicing.repository.OrderDao;
import com.dy.Invoicing.service.OrderService;
import com.dy.base.domain.UserModel;
import com.dy.base.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("orderService")
public class OrderServiceImpl  extends BaseServiceImpl<OrderDao,OrderModel, String> implements OrderService {

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        super(orderDao);
    }

}
