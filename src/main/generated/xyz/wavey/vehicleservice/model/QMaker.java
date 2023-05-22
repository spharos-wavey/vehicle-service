package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMaker is a Querydsl query type for Maker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMaker extends EntityPathBase<Maker> {

    private static final long serialVersionUID = -1198289795L;

    public static final QMaker maker = new QMaker("maker");

    public final BooleanPath foreignCar = createBoolean("foreignCar");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QMaker(String variable) {
        super(Maker.class, forVariable(variable));
    }

    public QMaker(Path<? extends Maker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaker(PathMetadata metadata) {
        super(Maker.class, metadata);
    }

}

