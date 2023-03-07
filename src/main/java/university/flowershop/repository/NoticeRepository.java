package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.Notice;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager em;

    public void save(Notice notice) {
        em.persist(notice);
    }

    public List<Notice> findAll() {
        return em.createQuery("select n from Notice n", Notice.class)
                .getResultList();
    }

    public Notice findById(Long id) { // {id}의 값에 맞는 공지사항을 불러오기
        return em.find(Notice.class, id);
    }

    public void delete(Notice notice) {
        em.remove(notice);
    }

}