package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.Cart;
import university.flowershop.domain.Member;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByMember(Member member); // 회원별 장바구니 조회

    Optional<Cart> findByMemberAndFlower(Member member, Flower flower); // 회원별 꽃 상품 조회
    Optional<Cart> findByMemberAndAccessory(Member member, Accessory accessory); // 회원별 악세서리 상품 조회
    void deleteByIdAndMember(Long cartId, Member member); // 회원의 장바구니에서 상품 삭제
    List<Cart> findAllByMember(Member member); // 회원별 장바구니 전체 조회


    Optional<Object> findByIdAndMember(Long cartId, Member member);//
}
