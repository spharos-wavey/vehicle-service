package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFrame is a Querydsl query type for Frame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFrame extends EntityPathBase<Frame> {

    private static final long serialVersionUID = -1204257370L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFrame frame = new QFrame("frame");

    public final xyz.wavey.vehicleservice.base.QBaseTimeEntity _super = new xyz.wavey.vehicleservice.base.QBaseTimeEntity(this);

    public final StringPath appearance = createString("appearance");

    public final StringPath capacity = createString("capacity");

    public final QCarBrand carBrand;

    public final StringPath carName = createString("carName");

    public final StringPath carType = createString("carType");

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Integer> defaultPrice = createNumber("defaultPrice", Integer.class);

    public final NumberPath<Integer> distancePrice = createNumber("distancePrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath manual = createString("manual");

    public final BooleanPath recommend = createBoolean("recommend");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QFrame(String variable) {
        this(Frame.class, forVariable(variable), INITS);
    }

    public QFrame(Path<? extends Frame> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFrame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFrame(PathMetadata metadata, PathInits inits) {
        this(Frame.class, metadata, inits);
    }

    public QFrame(Class<? extends Frame> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.carBrand = inits.isInitialized("carBrand") ? new QCarBrand(forProperty("carBrand")) : null;
    }

}

