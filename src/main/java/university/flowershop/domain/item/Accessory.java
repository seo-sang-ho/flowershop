package university.flowershop.domain.item;

import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.Cart;
import university.flowershop.domain.Order;
import university.flowershop.domain.Review;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Accessory {

    @Id
    @GeneratedValue
    @Column(name = "accessory_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String name;
    @NotNull
    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "accessory")
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accessory_id")
    private List<Review> reviews = new ArrayList<>();

}
