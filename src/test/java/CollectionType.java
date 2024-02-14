import org.example.member.domain.Address;
import org.example.member.domain.Child;
import org.example.member.domain.Member;
import org.example.member.domain.Parent;
import org.junit.Test;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
public class CollectionType {

    @Test
    public void collectionTypeExample(){

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Member member= new Member();
            member.setName("1");
            member.setAddress(new Address("c","Str","zip"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("c2","Str2","zip2"));
            member.getAddressHistory().add(new Address("c3","Str3","zip3"));
            em.persist(member);
            // 맴버에 소속된 푸드와 어드래스 히스토리들이 의존을 하기에
            // 맴버가 변경이 되면 자동적으로 변경이 된다.
            // 값타입 컬랙션들도 자동적으로 삽입이 되어 버린다.

            em.flush();
            em.clear();
            // @ElementCollection
            // 컬랙션 타입은 자동적으로 지연로딩이 된다.
            // 프록시 타입이다. 자연적으로
            Member findmember= em.find(Member.class,member.getId());

            List<Address> addressList = findmember.getAddressHistory();
            for(Address a: addressList){
                System.out.println("Address :: "+ a.getCity());
            }

            Set<String> favorFoods = findmember.getFavoriteFoods();
            for(String food : favorFoods){
                System.out.println("Food :: "+ food);
            }

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }




    @Test
    public void collectionTypeUpdate(){

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Member member= new Member();
            member.setName("1");
            member.setAddress(new Address("c","Str","zip"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("c2","Str2","zip2"));
            member.getAddressHistory().add(new Address("c3","Str3","zip3"));
            em.persist(member);


            em.flush();
            em.clear();

            Member findmember= em.find(Member.class,member.getId());
            // 값 타입은 통으로 변경해라
            Address changeAddress=  findmember.getAddress();
            findmember.setAddress(new Address("newCity", changeAddress.getStreet(), changeAddress.getZipcode()));

            // 컬랙션 값타입을 변경할때 주소참조라서.
            // 지우고 다시 변경해야한다.
            findmember.getFavoriteFoods().remove("치킨");
            findmember.getFavoriteFoods().add("쌈장");

            // 값타입은 식별자 개념이 없어 추적이 어렵다 .
            // 값타입에 대한 변경된 사항이 생길시 모든 데이터를 삭제 -> 값 타임 컬렉션에 남은 현재값을 다시 저장.
            findmember.getAddressHistory().remove(new Address("c2","Str2","zip2"));
            findmember.getAddressHistory().add(new Address("anothercity","Str2","zip2"));


            // 결론상 이런 방법은 쓰면 안된다.
            // 일대다 관계를 쓰는 것이 낫다.
//            @ElementCollection
//            @CollectionTable(name = "ADDRESS" , joinColumns =
//            @JoinColumn(name = "MEMBER_ID")
//            )
//            private List<Address> addressHistory =new ArrayList<>();

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }
}
