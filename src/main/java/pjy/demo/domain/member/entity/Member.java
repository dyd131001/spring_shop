package pjy.demo.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "member_table")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

//    public static Builder builder() {
//        return new Builder();
//    }
//
//    public static class Builder{
//        private Long id;
//        private String name;
//
//        public Builder id(Long id) {
//            this.id=id;
//            return this;
//        }
//        public Builder name(String name) {
//            this.name=name;
//            return this;
//        }
//
//        public Member build() {
//            return new Member(this.id,this.name);
//        }
//
//    }
}
