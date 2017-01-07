package com.yyqian.playground.mybatis.mapper;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public MapperBuilder() {
        this("mybatis.dynamic." + UUID.randomUUID().toString().replace("-", ""));
    }

    public MapperBuilder(String className) {
        this.className = className;
        target = prepare(className);
    }

    private CtClass prepare(String className) {
        ClassPool classPool = ClassPool.getDefault();
        CtClass target = classPool.makeInterface(className);
        CtClass fakeParent = classPool.makeClass(ViewMapper.class.getName());
        target.addInterface(fakeParent);
        return target;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MapperBuilder addStatement(Statement statement) {
        statements.add(statement);
        return this;
    }

    public MapperBuilder setSqlQuery(String sql) {
        statements.clear();
        statements.add(new Statement(sql, ViewMapper.class.getDeclaredMethods()[0].getName()));
        return this;
    }

    public Class<?> build() {
        String returnType = "java.util.List";
        String returnSignature =
                "()Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;>;";
        target.setName(className);
        statements.forEach(statement -> {
            String methodName = statement.getMethodName();
            CtMethod method = null;
            try {
                CtClass returnCtClass = ClassPool.getDefault().get(returnType);
                method = CtNewMethod.abstractMethod(returnCtClass, methodName,
                        null, null, target);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            SignatureAttribute signatureAttribute = new SignatureAttribute(
                    target.getClassFile().getConstPool(), returnSignature);
            MethodInfo methodInfo = method.getMethodInfo();
            methodInfo.addAttribute(signatureAttribute);
            // prepare annotation
            ConstPool constPool = target.getClassFile().getConstPool();
            ArrayMemberValue annoValue = new ArrayMemberValue(constPool);
            MemberValue value = new StringMemberValue(statement.getSql(), constPool);
            annoValue.setValue(new MemberValue[]{value});
            Annotation annotation = new Annotation(statement.getAnnotationName(), constPool);
            annotation.addMemberValue("value", annoValue);
            AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            attr.addAnnotation(annotation);
            methodInfo.addAttribute(attr);
            try {
                target.addMethod(method);
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        });
        Class<?> clazz = null;
        try {
            clazz = target.toClass();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
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

    private static void checkClass(Class<?> clazz) {
        LOGGER.info("Class name: {}", clazz.getName());
        for (Class<?> intf : clazz.getInterfaces()) {
            LOGGER.info("Extended interface: {}", intf.getName());
        }
        for (Method method : clazz.getMethods()) {
            LOGGER.info("Method name: {}", method.getName());
            LOGGER.info("Method return type: {}", method.getGenericReturnType().getTypeName());
            for (java.lang.annotation.Annotation annotation : method.getAnnotations()) {
                LOGGER.info("Method annotation: {}", annotation);
            }
        }
    }
}
