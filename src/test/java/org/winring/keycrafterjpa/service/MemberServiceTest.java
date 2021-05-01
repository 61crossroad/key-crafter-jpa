package org.winring.keycrafterjpa.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.winring.keycrafterjpa.domain.Member;
import org.winring.keycrafterjpa.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
