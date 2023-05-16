package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member findMember = em.find(Member.class, 1L);
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            em.persist(member); // 멤버 저장
            //findMember.setName("helloJPA"); // 멤버 수정

            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)   // JPQL 페이징 할 때 편리
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }*/
            // 비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("HelloB");

            // 영속 상태
            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        
        emf.close();
    }
}
