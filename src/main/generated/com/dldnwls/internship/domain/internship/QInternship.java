package com.dldnwls.internship.domain.internship;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInternship is a Querydsl query type for Internship
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInternship extends EntityPathBase<Internship> {

    private static final long serialVersionUID = -847588459L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInternship internship = new QInternship("internship");

    public final com.dldnwls.internship.domain.company.QCompany company;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final SetPath<com.dldnwls.internship.domain.techstack.Techstack, com.dldnwls.internship.domain.techstack.QTechstack> requiredSkills = this.<com.dldnwls.internship.domain.techstack.Techstack, com.dldnwls.internship.domain.techstack.QTechstack>createSet("requiredSkills", com.dldnwls.internship.domain.techstack.Techstack.class, com.dldnwls.internship.domain.techstack.QTechstack.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> salary = createNumber("salary", java.math.BigDecimal.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public QInternship(String variable) {
        this(Internship.class, forVariable(variable), INITS);
    }

    public QInternship(Path<? extends Internship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInternship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInternship(PathMetadata metadata, PathInits inits) {
        this(Internship.class, metadata, inits);
    }

    public QInternship(Class<? extends Internship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new com.dldnwls.internship.domain.company.QCompany(forProperty("company")) : null;
    }

}

