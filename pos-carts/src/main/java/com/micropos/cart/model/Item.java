package com.micropos.cart.model;

import com.micropos.dto.CartItemDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
@Accessors(fluent = true, chain = true)
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="product_id")
    public String productId;

    @Column(name = "quantity")
    @Getter
    @Setter
    public int quantity;

    public Item() {
        this.productId = "";
        this.quantity = 0;
    }
    public Item(String productId, int i) {
        this.productId = productId;
        this.quantity = i;
    }


    // Getters and setters
}
