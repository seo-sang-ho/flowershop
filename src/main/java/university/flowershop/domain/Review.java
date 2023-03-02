package university.flowershop.domain;

import lombok.Getter;
import lombok.Setter;
import university.flowershop.domain.item.Flower;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "content")
    private String content; // 리뷰 내용

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
