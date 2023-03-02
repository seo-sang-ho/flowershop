package university.flowershop.dto;

import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.Address;
import university.flowershop.domain.Member;

import javax.persistence.Embedded;

@Getter
@Setter
public class MemberDto {

    private Long id;

    private String name;

    private String loginId;

    private String password;

    private String phone;

    private String email;

    @Embedded
    private Address address;

    public MemberDto(Long id, String name, String loginId, String password, String phone, String email, Address address ) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public MemberDto(Long id, String name, String loginId, String password, String phone, String email, String city, String street, String zipcode) {
    }

    public static MemberDto createMemberDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getLoginId(),
                member.getPassword(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress()
        );
    }


    public String getCity() {
        return this.address.getCity();
    }

    public String getStreet() {
        return this.address.getStreet();
    }

    public String getZipcode() {
        return this.address.getZipcode();
    }
}
