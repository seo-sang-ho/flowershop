package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import university.flowershop.domain.Member;
import university.flowershop.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return 이 null 이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        Member member = memberRepository.loginUse(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        return member;
    }

}


