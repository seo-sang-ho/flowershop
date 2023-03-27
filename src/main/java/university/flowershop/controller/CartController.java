package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.domain.Member;
import university.flowershop.domain.Order;
import university.flowershop.service.CartService;
import university.flowershop.service.MemberService;
import university.flowershop.service.OrderService;
import university.flowershop.session.SessionConst;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final OrderService orderService;


    @GetMapping("/cart")
    public String cartAll() {
        return "cart/items";
    }

    @GetMapping("/cart/create")
    public String cartView() {
        return "cart/items";
    }

    @PostMapping("/cart/create")
    public String createCart(HttpSession httpSession) {

        Long memberId = (Long) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);

        Cart cart = cartService.findOne(memberId);

        cartService.save(cart);

        return "redirect:/cart/items";
    }

    @GetMapping("/cart/items")
    public String viewCartItems(Model model, HttpSession httpSession) {
        Member memberId = (Member) httpSession.getAttribute(SessionConst.LOGIN_MEMBER);
        Member member = memberService.findOne(memberId.getId());

        Cart cart = cartService.findOne(member.getId());

        if (cart == null || cart.getCartItems().isEmpty()) {
            return "redirect:/cart/empty";
        }

        List<CartItem> cartItems = cart.getCartItems();
        model.addAttribute("cartItems", cartItems);

        return "cart/items";
    }

    @GetMapping("/cart/empty")
    public String viewEmptyCart() {
        return "cart/emptyCart";
    }



    @GetMapping("/order/{cartId}")
    public String orderForm(Model model,
                            HttpSession session) {
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Member memberId = memberService.getMemberByName(member.getId());
        Cart cart = cartService.findOne(member.getId());

        // cart가 없거나, cart의 회원정보와 로그인된 회원정보가 다르면 에러처리
        if (cart == null || !cart.getMember().getId().equals(member.getId())) {
            throw new IllegalArgumentException("Invalid cart");
        }

        // 주문 페이지에 필요한 정보를 조회하고 모델에 담는다.
        List<CartItem> cartItems = cart.getCartItems();
        model.addAttribute("member",memberId);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("orderForm", new OrderForm());

        return "order/order";
    }

}
