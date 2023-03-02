package university.flowershop.repository;

import org.springframework.stereotype.Repository;
import university.flowershop.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository {

    private final EntityManager em;

    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findOrdersByMemberId(Long memberId) {
        return em.createQuery("select o from Order o join o.member m where m.id = :memberId", Order.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}
