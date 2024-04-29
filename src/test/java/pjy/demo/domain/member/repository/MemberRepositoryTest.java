package pjy.demo.domain.member.repository;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {

    // Test가 끝난 후 수행할 내용
    @AfterEach
    public void afterEach() {

    }

    // Test 실행 순서는 랜덤
    @Test
    public void save() {
        int a = 0;

        // isEqualTo는 ==, isSameAs는 ref 비교
        assertThat(a).isEqualTo(0);
    }
    @Test
    public void findById(){

    }
    @Test
    public void findByName(){

    }

    @Test
    public void findAll() {

    }
}
