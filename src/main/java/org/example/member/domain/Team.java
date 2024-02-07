package org.example.member.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team", cascade =  CascadeType.ALL)  // @ManyToOne 에 걸려있는것의 값으로
    // mappedBy 이 걸려있으면 읽기만 가능하다.
    // 외래키가 있는곳으로 주인을 정해라.
    // 현재 외래키는 맴버에 있으니/.
    private List<Member> members = new ArrayList<>();


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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addmembers(Member member){
        member.setTeam(this);
        members.add(member);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
