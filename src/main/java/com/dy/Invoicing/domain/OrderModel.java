package com.dy.Invoicing.domain;

import com.dy.base.domain.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
@Entity
@Table(name = "ORDERS")
public class OrderModel  extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid2" )   //指定生成器名称
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "BUY_UNIT_NAME")
    private String buyUnitName;//买方单位名称

    @Column(name = "SALE_UNIT_NAME")
    private String saleUnitName;//卖方单位名称

    @Column(name = "INVOICE_CODE")
    private String invoiceCode;//发票代码

    @Column(name = "INVOICE_NUM")
    private String invoiceNum;//发票号码

    @Column(name = "WRITE_DATE")
    private Date writeDate;//填写日期

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "orderModel")
    private List<OrderProduct> productList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
