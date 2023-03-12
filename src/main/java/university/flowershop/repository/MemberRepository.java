package university.flowershop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public Member getMemberByName(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Member> findByLoginId(String loginId) { //아이디 중복 체크
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }
    public String findPasswordByNameLoginId(String name,String loginId) { //id로 비밀번호 찾기
        return em.createQuery("select m.password from Member m where m.name = :name and m.loginId =:loginId", String.class)
                .setParameter("name",name)
                .setParameter("loginId",loginId)
                .getSingleResult();
    }

    public String findLoginIdByNamePhone(String name,String phone) { //비밀번호로 id 찾기
        return em.createQuery("select m.loginId from Member m where m.name = :name and m.phone =:phone", String.class)
                .setParameter("name",name)
                .setParameter("phone",phone)
                .getSingleResult();
    }

    public Optional<Member> loginUse(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void updateMember(Member member) {
        em.createQuery("UPDATE Member m SET m.name = :name, m.phone = :phone, m.loginId = :loginId, m.password = :password, m.email = :email, m.address.city = :city, m.address.street = :street, m.address.zipcode = :zipcode WHERE m.id = :id")
                .setParameter("name", member.getName())
                .setParameter("phone", member.getPhone())
                .setParameter("loginId", member.getLoginId())
                .setParameter("password", member.getPassword())
                .setParameter("email", member.getEmail())
                .setParameter("city", member.getAddress().getCity())
                .setParameter("street", member.getAddress().getStreet())
                .setParameter("zipcode", member.getAddress().getZipcode())
                .setParameter("id", member.getId())
                .executeUpdate();
    }

}
