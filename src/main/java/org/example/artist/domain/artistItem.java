package org.example.artist.domain;

import javax.persistence.*;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //  상속전략중
@DiscriminatorColumn   // Dtype 설정 어느테이블이 키값인지 안다.
public abstract class artistItem {

    @Id
    private Long id;

    private String name;

    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

// InheritanceType.JOINED 각 테이블이 생성  1대1 구도로
// 전략적으로 짜는것이 좋다.
// strategy = InheritanceType.SINGLE_TABLE  단일 테이블
// 확정 가능성이 없고 단순하면 이것이 좋다.

// @DiscriminatorColumn   // Dtype 설정 어느테이블이 키값인지 안다.  테이블명이 붙여짐
//
// @DiscriminatorValue(value = "A")  만일 테이블 명그대로 쓰기 싫은때 이렇게 바꿔도됨


//strategy = InheritanceType.TABLE_PER_CLASS 각 상속된 엔터티들에게 집어넣는 전략
//   abstract class  입력하여 추상화 클레스라는 것을 명시하는것이 중요한다.
// 추천 하지않는다.
// 오히려 @MappedSuperclass 이걸 권장한다.