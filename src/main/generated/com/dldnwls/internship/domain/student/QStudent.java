package com.dldnwls.internship.domain.student;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudent is a Querydsl query type for Student
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudent extends EntityPathBase<Student> {

    private static final long serialVersionUID = 1287596079L;

    public static final QStudent student = new QStudent("student");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath major = createString("major");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath resume = createString("resume");

    public final SetPath<com.dldnwls.internship.domain.techstack.Techstack, com.dldnwls.internship.domain.techstack.QTechstack> techStacks = this.<com.dldnwls.internship.domain.techstack.Techstack, com.dldnwls.internship.domain.techstack.QTechstack>createSet("techStacks", com.dldnwls.internship.domain.techstack.Techstack.class, com.dldnwls.internship.domain.techstack.QTechstack.class, PathInits.DIRECT2);

    public final StringPath university = createString("university");

    public QStudent(String variable) {
        super(Student.class, forVariable(variable));
    }

    public QStudent(Path<? extends Student> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudent(PathMetadata metadata) {
        super(Student.class, metadata);
    }

}

