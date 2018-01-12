package com.dy.Invoicing.domain;

import com.dy.base.domain.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单表
 */
@Entity
@Table(name = "orders")
public class OrderModel  extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid2" )   //指定生成器名称
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(name = "ID", nullable = false)
    private String id;

    private String unitName;//单位名称


    private String invoiceNum;//发票号码



}
