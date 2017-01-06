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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created on 2017-01-04T17:54:37+08:00.
 * TODO: Bug: 无法在 uber-jar 环境下获取 class
 * TODO: 改进生成的方法，减少消耗
 * @author Yinyin Qian
 */
public class MapperBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperBuilder.class);

    private final List<Statement> statements = new ArrayList<>();
    private final CtClass target = ClassPool.getDefault().getCtClass(TemplateMapper.class.getName());
    private String className;

    public MapperBuilder() throws NotFoundException {
        this("mybatis.dynamic." + UUID.randomUUID().toString().replace("-", ""));
    }

    public MapperBuilder(String className) throws NotFoundException {
        this.className = className;
    }

    private interface TemplateMapper extends WildMapper {
        @Override
        List<Map<String, Object>> selectAll();
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

    public Class<?> build() throws CannotCompileException, NoSuchMethodException {
        target.setName(className);
        statements.forEach(statement -> {
            ConstPool constPool = target.getClassFile().getConstPool();
            AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation(statement.getAnnotationName(), constPool);
            ArrayMemberValue annoValue = new ArrayMemberValue(constPool);
            MemberValue value = new StringMemberValue(statement.getSql(), constPool);
            annoValue.setValue(new MemberValue[]{value});
            annotation.addMemberValue("value", annoValue);
            LOGGER.info("Appending annotation: {}", annotation.toString());
            attr.addAnnotation(annotation);
            try {
                CtMethod ctMethod = target.getDeclaredMethod(statement.getMethodName());
                LOGGER.info("Modifying method: {}", ctMethod.getName());
                ctMethod.getMethodInfo().addAttribute(attr);
            } catch (NotFoundException e) {
                LOGGER.error(e.toString());
            }
        });
        Class<?> clazz = target.toClass();
        checkClass(clazz);
        return clazz;
    }

    public static class Statement {
        private final String sql;
        private final String methodName;
        private final String annotationName;

        public Statement(String sql, String methodName) {
            this(sql, methodName, "org.apache.ibatis.annotations.Select");
        }

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

    private static void checkClass(Class<?> clazz) throws NoSuchMethodException {
        LOGGER.info("Class name: {}", clazz.getName());
        for (Class<?> intf : clazz.getInterfaces()) {
            LOGGER.info("Extended interface: {}", intf.getName());
        }
        for (Method method : clazz.getMethods()) {
            LOGGER.info("Method name: {}", method.getName());
            LOGGER.info("Method return type: {}", method.getReturnType().getName());
            for (java.lang.annotation.Annotation annotation : method.getAnnotations()) {
                LOGGER.info("Method annotation: {}", annotation);
            }
        }
    }
}
