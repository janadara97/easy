package com.easy.easyorder.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    public List<OrderLineItem> orderLineItems;
}
