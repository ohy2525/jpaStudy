package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")   //연관관계 주인
    private Member member;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
}
