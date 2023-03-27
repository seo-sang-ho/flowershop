package university.flowershop.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class AccessoryRepository{

    private final EntityManager em;

    public void save(Accessory accessory) {
        if (accessory.getId() == null) {
            em.persist(accessory);
        } else {
            em.merge(accessory);
        }
    }

    public Accessory findById(Long id) {
        return em.find(Accessory.class, id);
    }

    public List<Accessory> findAll() {
        return em.createQuery("select a from Accessory a", Accessory.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        Accessory accessory = em.find(Accessory.class,id);
        if (accessory != null) {
            em.remove(accessory);
        }
    }

    public List<Accessory> Search(String keyword) {
        return em.createQuery("select a from Accessory a where a.name like :keyword", Accessory.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}