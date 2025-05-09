package umc.spring.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1798722207L;

    public static final QMember member = new QMember("member1");

    public final umc.spring.study.domain.common.QBaseEntity _super = new umc.spring.study.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<umc.spring.study.domain.enums.Gender> gender = createEnum("gender", umc.spring.study.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final ListPath<umc.spring.study.domain.mapping.Agree, umc.spring.study.domain.mapping.QAgree> memberAgreeList = this.<umc.spring.study.domain.mapping.Agree, umc.spring.study.domain.mapping.QAgree>createList("memberAgreeList", umc.spring.study.domain.mapping.Agree.class, umc.spring.study.domain.mapping.QAgree.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.study.domain.mapping.MemberMission, umc.spring.study.domain.mapping.QMemberMission> memberMissionList = this.<umc.spring.study.domain.mapping.MemberMission, umc.spring.study.domain.mapping.QMemberMission>createList("memberMissionList", umc.spring.study.domain.mapping.MemberMission.class, umc.spring.study.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final ListPath<umc.spring.study.domain.mapping.MemberFoodPreferences, umc.spring.study.domain.mapping.QMemberFoodPreferences> memberPreferList = this.<umc.spring.study.domain.mapping.MemberFoodPreferences, umc.spring.study.domain.mapping.QMemberFoodPreferences>createList("memberPreferList", umc.spring.study.domain.mapping.MemberFoodPreferences.class, umc.spring.study.domain.mapping.QMemberFoodPreferences.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<umc.spring.study.domain.enums.MemberStatus> status = createEnum("status", umc.spring.study.domain.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

