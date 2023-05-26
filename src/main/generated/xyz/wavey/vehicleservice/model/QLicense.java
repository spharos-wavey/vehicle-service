package xyz.wavey.vehicleservice.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLicense is a Querydsl query type for License
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLicense extends EntityPathBase<License> {

    private static final long serialVersionUID = -1171116486L;

    public static final QLicense license = new QLicense("license");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final StringPath birth = createString("birth");

    public final StringPath expireDate = createString("expireDate");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath issueDate = createString("issueDate");

    public final StringPath level = createString("level");

    public final StringPath licenseNumber = createString("licenseNumber");

    public final StringPath type = createString("type");

    public final StringPath userName = createString("userName");

    public QLicense(String variable) {
        super(License.class, forVariable(variable));
    }

    public QLicense(Path<? extends License> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLicense(PathMetadata metadata) {
        super(License.class, metadata);
    }

}

