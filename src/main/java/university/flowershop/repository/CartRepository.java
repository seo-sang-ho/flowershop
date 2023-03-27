package university.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }

    public Cart findById(Long id) {
        return em.find(Cart.class, id);
    }

    public List<Cart> findAll() {
        return em.createQuery("select c from Cart c", Cart.class)
                .getResultList();
    }


    public void delete(Cart cart) {
        em.remove(cart);
    }
}

