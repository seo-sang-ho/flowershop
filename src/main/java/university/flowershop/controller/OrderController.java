package university.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import university.flowershop.service.CartService;
import university.flowershop.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

//    @PostMapping("/order")
//    public String payInformation() {
//
//    }
}
