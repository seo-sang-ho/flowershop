package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.CartItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartItemRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(CartItem cartItem) {
        em.persist(cartItem);
    }

    public CartItem findById(Long id) {
        return em.find(CartItem.class, id);
    }

    public List<CartItem> findAll() {
        return em.createQuery("select ci from CartItem ci", CartItem.class)
                .getResultList();
    }

    public void delete(CartItem cartItem) {
        em.remove(cartItem);
    }
}
