package university.flowershop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    private int quantity;


    // 다대일 매핑으로 주문과 연결
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public Cart(Member member, Flower flower,Accessory accessory, int count) {
        this.member = member;
        this.flower = flower;
        this.accessory = accessory;
        this.quantity = count;

    }

    // 장바구니에 상품을 추가하는 메서드
    public void addCount() {
        this.quantity += 1;
    }

    // 장바구니에 상품을 제거하는 메서드
    public void removeCount() {
        this.quantity -= 1;
    }

    public void changeCount(int count) {
        this.quantity = count;
    }


    public void addCartItem(CartItem cartItem) {
        cartItem.setCart(this);
        cartItems.add(cartItem);
    }
}