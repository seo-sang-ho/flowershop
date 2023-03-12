package university.flowershop.domain.item;

import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@Table(name = "accessory")
public class Accessory {

    @Id
    @GeneratedValue
    @Column(name = "accessory_id")
    private Long id;

    @NotNull
    private String prdNum;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String name;
    @NotNull
    private int price;

    private int stockQuantity;

    private int quantity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accessory_id")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "flower")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "flower")
    private List<CartItem> cartItems = new ArrayList<>();

}
