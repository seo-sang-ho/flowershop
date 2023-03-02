package university.flowershop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;

    private int count;

    @Builder
    public Cart(Member member, Flower flower,Accessory accessory, int count) {
        this.member = member;
        this.flower = flower;
        this.accessory = accessory;
        this.count = count;

    }

    // 장바구니에 상품을 추가하는 메서드
    public void addCount() {
        this.count += 1;
    }

    // 장바구니에 상품을 제거하는 메서드
    public void removeCount() {
        this.count -= 1;
    }

    public void changeCount(int count) {
        this.count = count;
    }

}