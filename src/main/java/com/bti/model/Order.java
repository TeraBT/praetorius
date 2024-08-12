package com.bti.model;

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
    @GeneratedValue
    private Long id;

    @Column
    private String orderReference;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private double price;

    @Column
    private Integer amount;

    @Column
    private double totalPrice;

    @Column
    private String clientComment;

    @Column
    private String vendorNote;

    // TODO: Orders should keep info about vendor even if vendor is deleted (also for product)
    // TODO: Orders shouldn't have editable vendor and product info

    @Column
    private String orderLog;

    public String getCreateTimestampAsFormatedString() {
        if (createTimestamp == null) {
            return "NONE";
        } else {
            return createTimestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }

    public String createOrderLog() {
        return orderLog = "Order ID: " + id +
                "\nOrder Reference: " + orderReference +
                "\nVendor: " + vendor.getName() +
                "\nProduct: " + product.getName() +
                "\nAmount: " + amount + "\n";
    }

    public void setBuyer(User user) {
        if (user != null && this.buyer != null) {
            if (this.buyer.equals(user)) {
                return;
            }
        }

        if (user != null) {
            user.getOrderSet().add(this);
        }

        if (this.buyer != null) {
            this.buyer.getOrderSet().remove(this);
        }

        this.buyer = user;
    }


    public void setVendor(Vendor vendor) {
        if (vendor != null && this.vendor != null) {
            if (this.vendor.equals(vendor)) {
                return;
            }
        }

        if (vendor != null) {
            vendor.getOrderSet().add(this);
        }

        if (this.vendor != null) {
            this.vendor.getOrderSet().remove(this);
        }

        this.vendor = vendor;
    }

    public void setProduct(Product product) {
        if (product != null && this.product != null) {
            if (this.product.equals(product)) {
                return;
            }
        }

        if (product != null) {
            product.getOrderSet().add(this);
        }

        if (this.product != null) {
            this.product.getOrderSet().remove(this);
        }

        this.product = product;
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

    public User getBuyer() {
        return buyer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String textMessage) {
        this.clientComment = textMessage;
    }

    public String getVendorNote() {
        return vendorNote;
    }

    public void setVendorNote(String vendorNote) {
        this.vendorNote = vendorNote;
    }

    public String getOrderLog() {
        return orderLog;
    }

    public void setOrderLog(String orderLog) {
        this.orderLog = orderLog;
    }
}
