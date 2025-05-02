package umc.spring.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.study.domain.common.BaseEntity;
import umc.spring.study.domain.enums.Gender;
import umc.spring.study.domain.enums.MemberStatus;
import umc.spring.study.domain.mapping.Agree;
import umc.spring.study.domain.mapping.MemberFoodPreferences;
import umc.spring.study.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키를 만드는 방법
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Column(nullable = false, length = 40)
    private String address;

    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDateTime inactiveDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Agree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFoodPreferences> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    public void addMemberAgree(Agree agree) {
        memberAgreeList.add(agree);
        agree.setMember(this);
    }

}
