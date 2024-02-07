//package org.example;
//
//import org.example.member.domain.Member;
//import org.example.member.domain.Team;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import java.util.List;
//
//public class main2 {
//    public static void main(String[] args) {
//        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx= em.getTransaction();
//        tx.begin();
//
//        try{
//            Team team= new Team();
//            team.setName("TeamA");
//
//            em.persist(team);
//
//            Member member= new Member();
//            member.setName("member1");
//            member.setTeam(team);
//            em.persist(member);
//
//            Team findteam= em.find(Team.class,team.getId());
//            List<Member> members = findteam.getMembers();
//
//            em.flush();
//            em.clear();
//
//            System.out.println("===================================");
//            System.out.println("member"+findteam   );
//            System.out.println("===================================");
//
//
//            tx.commit();
//        }catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close();
//        }
//
//        em.close();
//        emf.close();
//
//
//    }
//}
