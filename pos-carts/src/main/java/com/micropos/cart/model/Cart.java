package com.micropos.cart.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    public List<Item> items = new ArrayList<>();

    @Getter
    @Setter
    @Column(name = "total")
    public Double total = 0.0;

    public void addItem(Item item) {
        items.stream()
                .filter(it -> it.productId.equals(item.productId))
                .findAny()
                .ifPresentOrElse(
                        it -> it.quantity += 1,
                        () -> {
                            items.add(item);
                        }
                );
    }

}
