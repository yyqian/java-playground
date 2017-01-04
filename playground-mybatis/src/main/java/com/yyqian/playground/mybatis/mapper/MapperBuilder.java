package com.yyqian.playground.mybatis.mapper;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017-01-04T17:54:37+08:00.
 *
 * @author Yinyin Qian
 */
public class MapperBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperBuilder.class);

    private final List<Statement> statements = new ArrayList<>();
    private final CtClass target;
    private String className;

    public MapperBuilder() throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        target = pool.getCtClass(BaseMapper.class.getName());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addStatement(Statement statement) {
        statements.add(statement);
    }

    public Class<?> build() throws CannotCompileException {
        target.setName(className);
        statements.forEach(statement -> {
            ConstPool constPool = target.getClassFile().getConstPool();
            AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation(statement.getAnnotationName(), constPool);
            ArrayMemberValue annoValue = new ArrayMemberValue(constPool);
            MemberValue value = new StringMemberValue(statement.getSql(), constPool);
            annoValue.setValue(new MemberValue[]{value});
            annotation.addMemberValue("value", annoValue);
            attr.addAnnotation(annotation);
            try {
                CtMethod ctMethod = target.getDeclaredMethod(statement.getMethodName());
                ctMethod.getMethodInfo().addAttribute(attr);
            } catch (NotFoundException e) {
                LOGGER.error(e.toString());
            }
        });
        return target.toClass();
    }

    public static class Statement {
        private final String sql;
        private final String methodName;
        private final String annotationName;

        public Statement(String sql, String methodName, String annotationName) {
            this.sql = sql;
            this.methodName = methodName;
            this.annotationName = annotationName;
        }

        public String getSql() {
            return sql;
        }

        public String getMethodName() {
            return methodName;
        }

        public String getAnnotationName() {
            return annotationName;
        }
    }
}
