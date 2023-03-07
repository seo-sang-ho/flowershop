package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@RequiredArgsConstructor
public class AdminRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Admin admin) {
        em.persist(admin);
    }
}
