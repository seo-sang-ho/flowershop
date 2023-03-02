package university.flowershop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    private String loginId;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<Notice> notices = new ArrayList<>();

}
