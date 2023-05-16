package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookList is a Querydsl query type for BookList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookList extends EntityPathBase<BookList> {

    private static final long serialVersionUID = -814983090L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookList bookList = new QBookList("bookList");

    public final xyz.wavey.vehicleservice.base.QBaseTimeEntity _super = new xyz.wavey.vehicleservice.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public final QVehicle vehicle;

    public QBookList(String variable) {
        this(BookList.class, forVariable(variable), INITS);
    }

    public QBookList(Path<? extends BookList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookList(PathMetadata metadata, PathInits inits) {
        this(BookList.class, metadata, inits);
    }

    public QBookList(Class<? extends BookList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vehicle = inits.isInitialized("vehicle") ? new QVehicle(forProperty("vehicle"), inits.get("vehicle")) : null;
    }

}

