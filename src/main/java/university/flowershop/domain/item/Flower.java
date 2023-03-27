package university.flowershop.domain.item;

import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.*;
import university.flowershop.exception.NotEnoughStockException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flower_id")
    private Long id;

    @NotNull
    private String prdNum;

    @NotNull
    private String name;
    @NotNull
    private int price;

    private int stockQuantity;

    @Column(columnDefinition = "TEXT")
    private String description;


    @OneToMany(mappedBy = "flower" , cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "flower")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "flower")
    private List<CartItem> cartItems = new ArrayList<>();

    //==비즈니스 로직==/

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stockQuantity = restStock;
    }
}
