package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import university.flowershop.domain.Address;
import university.flowershop.domain.Member;
import university.flowershop.repository.MemberRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }
    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    public Member getMemberByName(Long id) {
        return memberRepository.getMemberByName(id);
    }

    public String findLoginIdByPassword(String name,String phone) {
        return memberRepository.findLoginIdByNamePhone(name, phone);
    }

    public String findPasswordByNameLoginId(String name, String loginId) {
        return memberRepository.findPasswordByNameLoginId(name, loginId);
    }

    @Transactional
    public void updateMember(Long memberId, String name, String loginId, String password, String phone, String email, String city, String street, String zipcode) {
        Member member = memberRepository.findOne(memberId);
        member.setName(name);
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setPhone(phone);
        member.setEmail(email);
        member.setAddress(new Address(city,street,zipcode));
    }
}

