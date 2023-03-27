package university.flowershop.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    private String address;
    private String detailAddress;
    private String zipcode;

    private LocalDateTime receiveTime;

    public Recipient(String recipientName, String recipientPhone, String recipientAddress, String recipientDetailAddress, String recipientZipcode) {
        this.name = recipientName;
        this.phone = recipientPhone;
        this.address = recipientAddress;
        this.detailAddress = recipientDetailAddress;
        this.zipcode = recipientZipcode;
    }
}



