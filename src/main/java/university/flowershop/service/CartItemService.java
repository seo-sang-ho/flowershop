package university.flowershop.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.repository.CartItemRepository;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        log.info("cartItem에 등록됨");
    }

    public List<CartItem> AllCartItem() {
        return cartItemRepository.findAll();
    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

}
