package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.controller.OrderForm;
import university.flowershop.domain.*;
import university.flowershop.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }

    public List<Order> findAll(Long id) {
        return orderRepository.findOrdersByMemberId(id);
    }

    @Transactional
    public Order createOrder(Member member, OrderForm orderForm, Cart cart) {
        // Order 엔티티 생성
        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDERED);
        order.setPaymentMethod(orderForm.getPaymentMethod());

        // 수령인 정보 저장
        Recipient recipient = new Recipient();
        recipient.setName(orderForm.getRecipientName());
        recipient.setAddress(orderForm.getRecipientAddress());
        recipient.setPhone(orderForm.getRecipientPhone());
        order.setRecipient(recipient);

        // OrderItem 엔티티 생성
        List<CartItem> cartItems = cart.getCartItems(); // 장바구니에서 상품 목록을 가져옴

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(cartItem.getItem());
            orderItem.setOrderPrice(cartItem.getItem().getPrice());
            orderItem.setCount(cartItem.getCount());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        // 주문 완료 후, 장바구니 비우기
        cart.clearCart();

        return order;
    }


}
