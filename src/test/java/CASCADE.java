import org.example.member.domain.Child;
import org.example.member.domain.Member;
import org.example.member.domain.Parent;
import org.example.member.domain.Team;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Transactional
public class CASCADE {


    public void ex1(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Parent parent= new Parent();
            Child c1=new Child();
            Child c2=new Child();

            parent.addchild(c1);
            parent.addchild(c2);

//            em.persist(parent);
//            em.persist(c1);
//            em.persist(c2);
            //  대신에
            em.persist(parent);
            // 한번에 치면 다같이 insert 쳐준다.
            //  @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL) 해줘야한다.

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }


    @Test
    public void orphanRemoval(){  // orphanRemoval = true 부모객체가 자식 객체를 삭제 시키면 사라짐
        // 또는 부모객체가 삭제되면 자식객체도 전부 삭제된다.
        // 참조하는 곳이 하나일때 사용해야한다. 다중으로 잡혀있다면 사용금지.
        // orphanRemoval = true  CASCADE.ALL 둘다하면  부모객체가 자식 객체를 생사를 쥘수 있다.
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Parent parent= new Parent();
            Child c1=new Child();
            Child c2=new Child();

            parent.addchild(c1);
            parent.addchild(c2);

            em.persist(parent);
            em.flush();
            em.clear();

            Parent findparent= em.find(Parent.class,parent.getId());
            em.remove(findparent);
            // 이럴경우 전부 사라짐 아이들 객체도

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();
    }


}
