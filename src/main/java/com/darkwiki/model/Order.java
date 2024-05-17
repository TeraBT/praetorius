package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "order_table")
public class Order implements Persistable<String>, Serializable, Comparable<Order> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue()
    private Long id;

    @Column
    private String orderReference;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createTimestamp;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private double amount;

    @Column
    private String textMessage;


    public void setVendor(Vendor vendor) {
        if (vendor != null) {
            vendor.getOrderSet().add(this);
        }

        if (this.vendor != null) {
            this.vendor.getOrderSet().remove(this);
        }

        this.vendor = vendor;
    }

    public void setProduct(Product product) {
        if (product != null) {
            product.getOrderSet().add(this);
        }

        if (this.product != null) {
            this.product.getOrderSet().remove(this);
        }

        this.product = product;
    }


    public String getCreateTimestampAsFormatedString() {
        if (createTimestamp == null) {
            return "NONE";
        } else {
            return createTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int compareTo(Order o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public LocalDateTime getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(LocalDateTime createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
