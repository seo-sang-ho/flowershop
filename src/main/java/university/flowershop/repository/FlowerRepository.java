package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.item.Flower;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FlowerRepository{

    private final EntityManager em;

    public void save(Flower flower) {
        if (flower.getId() == null) {
            em.persist(flower);
        } else {
            em.merge(flower);
        }
    }

    public Flower findById(Long id) {
        return em.find(Flower.class, id);
    }

    public List<Flower> findAll() {
        return em.createQuery("select f from Flower f", Flower.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        Flower flower = em.find(Flower.class,id);
        if (flower != null) {
            em.remove(flower);
        }
    }

    public List<Flower> Search(String keyword) {
        return em.createQuery("select f from Flower f where f.name like :keyword", Flower.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();

    }
}
