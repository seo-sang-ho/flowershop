package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.domain.item.Accessory;
import university.flowershop.repository.CartRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
        log.info("cart에 등록됨");
    }

    public Cart findOne(Long id) {
        return cartRepository.findById(id);
    }

    public List<CartItem> findAllCartItems(Long id) {
        Cart cart = cartRepository.findById(id);
        return cart.getCartItems();
    }
}
