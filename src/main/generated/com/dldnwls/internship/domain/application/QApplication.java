package com.dldnwls.internship.domain.application;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QApplication is a Querydsl query type for Application
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplication extends EntityPathBase<Application> {

    private static final long serialVersionUID = 797712729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QApplication application = new QApplication("application");

    public final DateTimePath<java.time.LocalDateTime> applied_at = createDateTime("applied_at", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.dldnwls.internship.domain.internship.QInternship internship;

    public final StringPath resume = createString("resume");

    public final StringPath status = createString("status");

    public final com.dldnwls.internship.domain.student.QStudent student;

    public QApplication(String variable) {
        this(Application.class, forVariable(variable), INITS);
    }

    public QApplication(Path<? extends Application> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QApplication(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QApplication(PathMetadata metadata, PathInits inits) {
        this(Application.class, metadata, inits);
    }

    public QApplication(Class<? extends Application> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.internship = inits.isInitialized("internship") ? new com.dldnwls.internship.domain.internship.QInternship(forProperty("internship"), inits.get("internship")) : null;
        this.student = inits.isInitialized("student") ? new com.dldnwls.internship.domain.student.QStudent(forProperty("student")) : null;
    }

}

