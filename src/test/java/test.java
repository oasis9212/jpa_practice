import org.example.member.domain.Member;
import org.example.member.domain.Team;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@Transactional
public class test {

    @Test
    public void one(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Team team =new Team();
            team.setName("teamA");
            em.persist(team);
            Member member= new Member();
            member.setName("gghoelo");
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            Member findmemeber = em.find(Member.class, member.getId());

            //  지연로딩을 안했을 경우 해당 맹버의 전체 조인된 것을  호출한다 -> 비효율적
            // 따라서 지연로딩을 만들면 해당 테이블만 부른 상태
            System.out.println("findmemeber ::"+ findmemeber.getName());
            // 현재 조인된 프록시의 값은 객체만 생성될뿐아다.  ( 따라서 빈값이다.)
            System.out.println("getTeam().getClass() ::"+ findmemeber.getTeam().getClass());

            // 해당 칼럼을 호출을 할 때 쿼리를 호출하여 값을 불러준다.
            System.out.println("==================");
            System.out.println(findmemeber.getTeam().getName());
            System.out.println("==================");
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();

    }
}
