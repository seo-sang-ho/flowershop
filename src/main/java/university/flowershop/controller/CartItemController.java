package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.domain.Member;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;
import university.flowershop.service.*;
import university.flowershop.session.SessionConst;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CartItemController {

    private final FlowerService flowerService;
    private final AccessoryService accessoryService;
    private final CartItemService cartItemService;
    private final CartService cartService;
    private final MemberService memberService;


//    @PostMapping("/cart/add/flower")
//    public String addFlowerIntoCart(@RequestParam("flowerId") Long flowerId,
//                                    @RequestParam("quantity") int quantity) {
//
//        Flower flower = flowerService.findOne(flowerId);
//
//        CartItem cartItem = new CartItem();
//        cartItem.setFlower(flower);
//        cartItem.setQuantity(quantity);
//
//        cartItemService.save(cartItem);
//
//        return "redirect:/cart/items";
//
//    }

    @PostMapping("/cart/add/flower")
    public String addFlowerIntoCart(@RequestParam("flowerId") Long flowerId,
                                    @RequestParam("quantity") int quantity,
                                    HttpSession httpSession) {
        Member memberId = (Member) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
        Member member = memberService.findOne(memberId.getId());
        Cart cart = cartService.findOne(memberId.getId());

        if (cart == null) {
            cart = new Cart();
            cart.setMember(member);
        }

            Flower flower = flowerService.findOne(flowerId);

        // 장바구니에 이미 해당 상품이 있는 경우
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getFlower() != null && cartItem.getFlower().getId().equals(flowerId)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartService.save(cart);
                return "redirect:/cart/items";
            }
        }

            CartItem cartItem = new CartItem();
            cartItem.setFlower(flower);
            cartItem.setQuantity(quantity);

            cart.addItem(cartItem);
            cartService.save(cart);

            return "redirect:/cart/items";
        }

    @PostMapping("/cart/add/accessory")
    public String addAccessoryIntoCart(@RequestParam("accessoryId") Long accessoryId,
                                    @RequestParam("quantity") int quantity,
                                    HttpSession httpSession) {
        Member memberId = (Member) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
        Member member = memberService.findOne(memberId.getId());
        Cart cart = cartService.findOne(memberId.getId());

        if (cart == null) {
            cart = new Cart();
            cart.setMember(member);
        }

        Accessory accessory = accessoryService.findOne(accessoryId);

        // 장바구니에 이미 해당 상품이 있는 경우
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getAccessory() != null && cartItem.getAccessory().getId().equals(accessoryId)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartService.save(cart);
                return "redirect:/cart/items";
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setAccessory(accessory);
        cartItem.setQuantity(quantity);

        cart.addItem(cartItem);
        cartService.save(cart);

        return "redirect:/cart/items";
    }

}
