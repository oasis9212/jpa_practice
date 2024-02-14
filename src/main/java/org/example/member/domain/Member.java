package org.example.member.domain;


import org.example.artist.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="member")
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    // 일대다 단방향 매핑 보단  다대일 양방향 매핑을 사용하자.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members")
    private Team team;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
                    @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods= new HashSet<>();



    @ElementCollection
    @CollectionTable(name = "ADDRESS" , joinColumns =
    @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address> addressHistory =new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public Team getTeam() {
        return team;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }


}


    // 조인  할때   현재클래스  to 조인시킬클래스
    // ex 맴버는 n개이고 팀은 1개다.
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;