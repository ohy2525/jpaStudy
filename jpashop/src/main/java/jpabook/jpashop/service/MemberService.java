package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //트랜잭션이 꼭 필요. 각 메소드 별로 readOnly 옵션을 주면 최적화 쿼리 성능 향상
@RequiredArgsConstructor //final 있는 필드만 가지고 생성자 생성
public class MemberService {

    private final MemberRepository memberRepository;

    /*public MemberService(MemberRepository memberRepository) {  //생성자가 한 개면 @Autowired 없어도 됨
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원 가입
     */
    @Transactional //따로 설정하면 이게 우선권
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //동시에 같은 이름으로 가입하게 되면 문제가 발생. 실무에서는 name 유니크 제약조건 필요
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
