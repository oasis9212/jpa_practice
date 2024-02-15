import org.example.member.domain.Child;
import org.example.member.domain.Member;
import org.example.member.domain.Parent;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class Jpql {



    @Test
    public void CriteriaEx(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{
                // Criteria 사용방법
            CriteriaBuilder cb= em.getCriteriaBuilder();
            CriteriaQuery<Member> query= cb.createQuery(Member.class);
            Root<Member> m =query.from(Member.class);

            CriteriaQuery<Member>  query2=query.select(m).where(cb.equal(m.get("name"),"kim"));
            //변형  동적 쿼리 활용방식.
            CriteriaQuery<Member>  query3=query.select(m);
            String name="fff";
            if(name!=null){
                query3.where(cb.equal(m.get("name"),"kim"));
            }

          //  em.createQuery(query2).getResultList();
            em.createQuery(query3).getResultList();


            /*   안쓴다. sql 스럽지안아서  너무 복잡함.  */

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }



    @Test
    public void   CreateNativeEx(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{
            // 영속성 persist 하고나서 flush() 호출이 갠찬음.
            //
            Member m=new Member();
            m.setName("rororl");
            em.persist(m);
            // 그냥 그대로 쿼리를 쓰는 방법.
            List<Member> result =em.createNativeQuery("SELECT  name,city,street from member",Member.class).getResultList();
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
