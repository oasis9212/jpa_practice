package org.example;

import org.example.artist.domain.Movie;
import org.example.member.domain.Member;
import org.example.member.domain.Order;
import org.example.member.domain.orderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class main3 {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx= em.getTransaction();
        tx.begin();

        try{

            Member member= new Member();
            member.setName("gghoelo");
            em.persist(member);
            em.flush();
            em.clear();

            Member findmemeber = em.getReference(Member.class, member.getId());

            System.out.println("findmemeber ::"+ findmemeber.getName());
            // 프록시일때는 아예 객체만 달려있는 빈값으로 설정이 되어있다.
            // 그래서 값을 호출할때 쿼리를 발생시킨 뒤 호출한다.
            // 호출 이후에는 레퍼런스가 초기화 된다.

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        em.close();
        emf.close();


    }
}
