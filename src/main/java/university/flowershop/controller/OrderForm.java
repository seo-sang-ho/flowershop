package university.flowershop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {

    // 주문자 정보
    private String ordererName;
    private String ordererPhone;

    // 수령자 정보
    private String recipientName;
    private String recipientAddress;
    private String recipientDetailAddress;
    private String recipientZipcode;

    private String recipientPhone;

    private String deliveryDate;

    // 결제 방법
    private String paymentMethod;
}
