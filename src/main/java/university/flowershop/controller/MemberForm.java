package university.flowershop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "로그인 ID를 입력하세요.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    private String password;

    @NotEmpty(message = "휴대폰 번호를 입력하세요.")
    private String phone;

    @NotEmpty(message = "이메일을 입력하세요.")
    private String email;

    @NotEmpty(message = "주소를 입력하세요.")
    private String city;
    private String street;
    private String zipcode;
}
