package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Address;
import org.winring.keycrafterjpa.domain.Member;
import org.winring.keycrafterjpa.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void createMembers() {
        int n = 20;
        for (int i = 1; i <= n; i++) {
            Member member = new Member();
            member.setName("member " + i);
            member.setAddress(new Address(
                    "city " + (i * 10),
                    "street " + (i * 100),
                    (i * 20) + "-" + (i * 5)));

            memberService.join(member);
        }
    }

    @Test
    public void findMemberTest() {
        List<Member> result = memberService.findMembers();
        Long id = (long) (result.size() / 2);
        Member savedMember = result.stream()
                .filter(m -> m.getId() == id)
                .findAny()
                .orElseThrow(NoSuchElementException::new);

        Member foundMember = memberService.findOne(id);

        assertEquals(foundMember, savedMember);
    }

    @Test
    public void joinTest() {
        // Given
        Member member = new Member();
        member.setName("kim");

        // When
        Long saveId = memberService.join(member);

        // Then
        assertEquals(member, memberRepository.findOne(saveId));
        // Without @Transactional case
        // assertEquals(member.getName(), memberRepository.findOne(saveId).getName());
    }

    @Test
    public void duplicatedMemberException() {
        // Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // When
        try {
            memberService.join(member1);
            memberService.join(member2);
        } catch (Exception e) {
            // Then
            assertTrue(e instanceof IllegalStateException);
        }
    }
}
