package com.dy.Invoicing.domain;

import com.base.orm.core.DBUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ORDER_PRODUCT")
public class OrderProduct {

    @Id
    @GeneratedValue(generator = "uuid2" )   //指定生成器名称
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(name = "ID", nullable = false)
    private String id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderModel orderModel;

    @Column(name = "PRODUCT_NAME")
    private String productName;//名称

    @Column(name = "PRODUCT_CATEGORY")
    private String productCategory;//类别

    @Column(name = "NUM")
    private String num;//数量

    @Column(name = "PRICE")
    private Double price;//单价

    @Column(name = "TAX_RATE")
    private Double taxRate;//税率

    @Column(name = "TAX_AMOUNT")
    private Double taxAmount;//税额

    @Column(name = "SPECIFICATION")
    private String specification;//规格

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setOrderId(String orderId) {
        if (orderId != null) {
            this.setOrderModel(DBUtil.find(OrderModel.class, orderId));
        }
    }

    @Transient
    public String getDictTypeId() {
        return this.orderModel == null ? null : this.orderModel.getId();
    }


}
