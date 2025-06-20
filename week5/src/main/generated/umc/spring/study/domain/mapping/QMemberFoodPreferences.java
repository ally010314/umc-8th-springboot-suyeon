package umc.spring.study.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberFoodPreferences is a Querydsl query type for MemberFoodPreferences
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberFoodPreferences extends EntityPathBase<MemberFoodPreferences> {

    private static final long serialVersionUID = 35363321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberFoodPreferences memberFoodPreferences = new QMemberFoodPreferences("memberFoodPreferences");

    public final umc.spring.study.domain.common.QBaseEntity _super = new umc.spring.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.spring.study.domain.QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.spring.study.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberFoodPreferences(String variable) {
        this(MemberFoodPreferences.class, forVariable(variable), INITS);
    }

    public QMemberFoodPreferences(Path<? extends MemberFoodPreferences> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberFoodPreferences(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberFoodPreferences(PathMetadata metadata, PathInits inits) {
        this(MemberFoodPreferences.class, metadata, inits);
    }

    public QMemberFoodPreferences(Class<? extends MemberFoodPreferences> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new umc.spring.study.domain.QFood(forProperty("food")) : null;
        this.member = inits.isInitialized("member") ? new umc.spring.study.domain.QMember(forProperty("member")) : null;
    }

}

