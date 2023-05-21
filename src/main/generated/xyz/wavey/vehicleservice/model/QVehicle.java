package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVehicle is a Querydsl query type for Vehicle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVehicle extends EntityPathBase<Vehicle> {

    private static final long serialVersionUID = -995804891L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicle vehicle = new QVehicle("vehicle");

    public final xyz.wavey.vehicleservice.base.QBaseTimeEntity _super = new xyz.wavey.vehicleservice.base.QBaseTimeEntity(this);

    public final BooleanPath available = createBoolean("available");

    public final NumberPath<Integer> charge = createNumber("charge", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final MapPath<String, Object, SimplePath<Object>> feature = this.<String, Object, SimplePath<Object>>createMap("feature", String.class, Object.class, SimplePath.class);

    public final QFrame frame;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> lastZone = createNumber("lastZone", Long.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final NumberPath<Integer> mileage = createNumber("mileage", Integer.class);

    public final StringPath number = createString("number");

    public final StringPath smartKey = createString("smartKey");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final DateTimePath<java.time.LocalDateTime> washTime = createDateTime("washTime", java.time.LocalDateTime.class);

    public QVehicle(String variable) {
        this(Vehicle.class, forVariable(variable), INITS);
    }

    public QVehicle(Path<? extends Vehicle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVehicle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVehicle(PathMetadata metadata, PathInits inits) {
        this(Vehicle.class, metadata, inits);
    }

    public QVehicle(Class<? extends Vehicle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.frame = inits.isInitialized("frame") ? new QFrame(forProperty("frame")) : null;
    }

}

