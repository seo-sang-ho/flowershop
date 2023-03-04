package university.flowershop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String loginId;

    private String password;

    private String phone;

    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts = new ArrayList<>();


    public String getCity() {
        return this.address.getCity();
    }

    public String getStreet() {
        return this.address.getStreet();
    }

    public String getZipcode() {
        return this.address.getZipcode();
    }


}
