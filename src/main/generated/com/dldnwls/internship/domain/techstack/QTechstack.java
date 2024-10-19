package com.dldnwls.internship.domain.techstack;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTechstack is a Querydsl query type for Techstack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTechstack extends EntityPathBase<Techstack> {

    private static final long serialVersionUID = 1220477853L;

    public static final QTechstack techstack = new QTechstack("techstack");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<com.dldnwls.internship.domain.internship.Internship, com.dldnwls.internship.domain.internship.QInternship> internships = this.<com.dldnwls.internship.domain.internship.Internship, com.dldnwls.internship.domain.internship.QInternship>createSet("internships", com.dldnwls.internship.domain.internship.Internship.class, com.dldnwls.internship.domain.internship.QInternship.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final SetPath<com.dldnwls.internship.domain.student.Student, com.dldnwls.internship.domain.student.QStudent> students = this.<com.dldnwls.internship.domain.student.Student, com.dldnwls.internship.domain.student.QStudent>createSet("students", com.dldnwls.internship.domain.student.Student.class, com.dldnwls.internship.domain.student.QStudent.class, PathInits.DIRECT2);

    public QTechstack(String variable) {
        super(Techstack.class, forVariable(variable));
    }

    public QTechstack(Path<? extends Techstack> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTechstack(PathMetadata metadata) {
        super(Techstack.class, metadata);
    }

}

