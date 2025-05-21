package umc.spring.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = 610219237L;

    public static final QFood food = new QFood("food");

    public final umc.spring.study.domain.common.QBaseEntity _super = new umc.spring.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<umc.spring.study.domain.mapping.MemberFoodPreferences, umc.spring.study.domain.mapping.QMemberFoodPreferences> memberFoodPreferencesList = this.<umc.spring.study.domain.mapping.MemberFoodPreferences, umc.spring.study.domain.mapping.QMemberFoodPreferences>createList("memberFoodPreferencesList", umc.spring.study.domain.mapping.MemberFoodPreferences.class, umc.spring.study.domain.mapping.QMemberFoodPreferences.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final ListPath<Store, QStore> storeFoodList = this.<Store, QStore>createList("storeFoodList", Store.class, QStore.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

