package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBillitaZone is a Querydsl query type for BillitaZone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBillitaZone extends EntityPathBase<BillitaZone> {

    private static final long serialVersionUID = 308646132L;

    public static final QBillitaZone billitaZone = new QBillitaZone("billitaZone");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final StringPath name = createString("name");

    public final StringPath regionName = createString("regionName");

    public final StringPath zoneAddress = createString("zoneAddress");

    public QBillitaZone(String variable) {
        super(BillitaZone.class, forVariable(variable));
    }

    public QBillitaZone(Path<? extends BillitaZone> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBillitaZone(PathMetadata metadata) {
        super(BillitaZone.class, metadata);
    }

}

