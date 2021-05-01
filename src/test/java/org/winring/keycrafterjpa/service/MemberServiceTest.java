package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Member;
import org.winring.keycrafterjpa.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

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
