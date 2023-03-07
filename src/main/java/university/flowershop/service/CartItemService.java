package university.flowershop.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.repository.CartItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> AllCartItem() {
        return cartItemRepository.findAll();
    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

}
