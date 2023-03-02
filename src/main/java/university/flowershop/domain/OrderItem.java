package university.flowershop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;

    private int orderPrice;

    private int count;

    public static OrderItem createOrderItem(Flower flower, Accessory accessory, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setFlower(flower);
        orderItem.setAccessory(accessory);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        return orderItem;
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
