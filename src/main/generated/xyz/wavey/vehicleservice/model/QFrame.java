package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFrame is a Querydsl query type for Frame
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFrame extends EntityPathBase<Frame> {

    private static final long serialVersionUID = -1204257370L;

    public static final QFrame frame = new QFrame("frame");

    public final xyz.wavey.vehicleservice.base.QBaseTimeEntity _super = new xyz.wavey.vehicleservice.base.QBaseTimeEntity(this);

    public final StringPath appearance = createString("appearance");

    public final StringPath capacity = createString("capacity");

    public final StringPath carType = createString("carType");

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Integer> defaultPrice = createNumber("defaultPrice", Integer.class);

    public final NumberPath<Integer> distancePrice = createNumber("distancePrice", Integer.class);

    public final BooleanPath foreignCar = createBoolean("foreignCar");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath maker = createString("maker");

    public final StringPath manual = createString("manual");

    public final StringPath name = createString("name");

    public final BooleanPath recommend = createBoolean("recommend");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QFrame(String variable) {
        super(Frame.class, forVariable(variable));
    }

    public QFrame(Path<? extends Frame> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFrame(PathMetadata metadata) {
        super(Frame.class, metadata);
    }

}

