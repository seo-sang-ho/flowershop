package university.flowershop.domain;


import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CartItem {

    @Id @GeneratedValue
    @Column(name = "cartItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;

    private int quantity;

}
