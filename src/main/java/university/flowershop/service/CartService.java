package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Cart;
import university.flowershop.domain.Member;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;
import university.flowershop.repository.AccessoryRepository;
import university.flowershop.repository.CartRepository;
import university.flowershop.repository.FlowerRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final FlowerRepository flowerRepository;
    private final AccessoryRepository accessoryRepository;

    /**
     * 회원의 장바구니에 꽃 상품 추가
     */
    public void addFlowerToCart(Member member, Long flowerId, int count) {
        Flower flower = flowerRepository.findById(flowerId);
        Cart cart = cartRepository.findByMemberAndFlower(member, flower)
                .orElse(Cart.builder().member(member).flower(flower).build());
        cart.addCount(); // count 값을 1 증가시키는 메서드 호출
        cartRepository.save(cart);
    }

    /**
     * 회원의 장바구니에 악세서리 상품 추가
     */
    public void addAccessoryToCart(Member member, Long accessoryId, int count) {
        Accessory accessory = accessoryRepository.findById(accessoryId);
        Cart cart = cartRepository.findByMemberAndAccessory(member, accessory)
                .orElse(Cart.builder().member(member).accessory(accessory).build());
        cart.addCount();
        cartRepository.save(cart);
    }

    /**
     * 회원의 장바구니에서 상품 수량 변경
     */
    public void changeItemCount(Member member, Long cartId, int count) {
        Cart cart = (Cart) cartRepository.findByIdAndMember(cartId, member)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 장바구니입니다."));
        cart.changeCount(count);
    }

    /**
     * 회원의 장바구니에서 상품 삭제
     */
    public void deleteCartItem(Member member, Long cartId) {
        cartRepository.deleteByIdAndMember(cartId, member);
    }

    /**
     * 회원의 장바구니 전체 조회
     */
    public List<Cart> getCartList(Member member) {
        return cartRepository.findAllByMember(member);
    }

}

