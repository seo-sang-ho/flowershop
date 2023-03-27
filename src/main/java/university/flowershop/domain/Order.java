package university.flowershop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import university.flowershop.controller.OrderForm;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate; //주문 시간


    //주문상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 결제 방법
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Recipient createReceiver(String name, String phoneNumber, String address, String detailAddress, String zipcode) {
        Recipient recipient = new Recipient();
        recipient.setName(name);
        recipient.setPhone(phoneNumber);
        recipient.setAddress(address);
        recipient.setDetailAddress(detailAddress);
        recipient.setZipcode(zipcode);
        return recipient;
    }

    public static Order createOrder(Member member, Recipient recipient, List<OrderItem> orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setRecipient(recipient);
        order.setStatus(OrderStatus.ORDERED);
        order.setOrderDate(LocalDateTime.now());

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }
}


