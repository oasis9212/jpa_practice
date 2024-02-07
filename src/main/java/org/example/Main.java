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
//public class Main {
//    public static void main(String[] args) {
//        EntityManagerFactory emf=Persistence.createEntityManagerFactory("hello");
//
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx= em.getTransaction();
//        tx.begin();
//
//        try{
//
//            Team team= new Team();
//            team.setName("TeamA");
//            em.persist(team);
//            Member member= new Member();
//            member.setName("member1");
//            member.setTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            Member findmember= em.find(Member.class,member.getId());
//            List<Member> members = findmember.getTeam().getMembers();
//
//            for(Member m: members){
//                System.out.println(m.getName());
//            }
//
//            Team findteam= findmember.getTeam();
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