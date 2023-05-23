package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarBrand is a Querydsl query type for CarBrand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarBrand extends EntityPathBase<CarBrand> {

    private static final long serialVersionUID = 1436824890L;

    public static final QCarBrand carBrand = new QCarBrand("carBrand");

    public final StringPath brandName = createString("brandName");

    public final BooleanPath foreignCar = createBoolean("foreignCar");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCarBrand(String variable) {
        super(CarBrand.class, forVariable(variable));
    }

    public QCarBrand(Path<? extends CarBrand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarBrand(PathMetadata metadata) {
        super(CarBrand.class, metadata);
    }

}

