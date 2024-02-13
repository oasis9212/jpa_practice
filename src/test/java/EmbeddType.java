import org.example.member.domain.Address;
import org.example.member.domain.Member;
import org.example.member.domain.Period;
import org.example.member.domain.Team;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

//@Transactional
public class EmbeddType {


    @Test
    public void EmbeddTypeExample(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{
            Address address=new Address("city","street","zipcode");
            Member m = new Member();
            m.setName("member1");
            m.setAddress(address);
            em.persist(m);

            Member m2 = new Member();
            m2.setName("member2");
            m2.setAddress(address);
            em.persist(m2);


            // 같이 변경된다.  m2 만 변경할려하지만 m 도변경되는 버그가 걸림.
            m2.getAddress().setCity("newCity");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }



    public void EmbeddTypeExample2(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{
            Address address=new Address("city","street","zipcode");
            Member m = new Member();
            m.setName("member1");
            m.setAddress(address);
            em.persist(m);

            // 차라리 다른 객체로 새로 선언하는 것이 좋다.
            // Embedd 타입은 불변 객체로 사용하는 것이 좋다. integer 나 String 객체로
            // setter를 삭제핳는것이 좋다.
            Address address2= new Address("Anothercity","Anotherstreet","Anotherzipcode");
            Member m2 = new Member();
            m2.setName("member2");
            m2.setAddress(address2);
            em.persist(m2);


                // 본의아니게 m1의 값도 변화한다.
//            m2.getAddress().setCity("newCity");

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
