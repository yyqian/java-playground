package com.yyqian.playground.mybatis.util;

import com.yyqian.playground.mybatis.demo.WildMapper;

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

/**
 * Created on 2017-01-04T17:32:32+08:00.
 *
 * @author Yinyin Qian
 */
public class ByteCodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ByteCodeUtil.class);

    private ByteCodeUtil() {
    }

    public static Class<?> genMapper(String name, String sql) {

    }

    public static Class<?> genGenericClass() throws CannotCompileException, NotFoundException, NoSuchMethodException {
        String sql = "select * from user";
        String name = "mybatis.GenMapper";
        ClassPool pool = ClassPool.getDefault();
        CtClass targetClass = pool.getCtClass(WildMapper.class.getName());
        targetClass.setName(name);

        ConstPool constPool = targetClass.getClassFile().getConstPool();
        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("org.apache.ibatis.annotations.Select", constPool);
        ArrayMemberValue annoValue = new ArrayMemberValue(constPool);
        MemberValue value = new StringMemberValue(sql, constPool);
        annoValue.setValue(new MemberValue[]{value});
        annotation.addMemberValue("value", annoValue);
        attr.addAnnotation(annotation);

        CtMethod ctMethod = targetClass.getDeclaredMethod("selectAll");
        ctMethod.getMethodInfo().addAttribute(attr);

        Class<?> clazz =  targetClass.toClass();
        checkClass(clazz);
        return clazz;
    }

    private static void checkClass(Class<?> clazz) throws NoSuchMethodException {
        Method method = (clazz.getMethods())[0];
        LOGGER.info("Class name: {}", clazz.getName());
        LOGGER.info("Method name: {}", method.getName());
        LOGGER.info("Method return type: {}", method.getReturnType().getName());
        for (java.lang.annotation.Annotation annotation : method.getDeclaredAnnotations()) {
            LOGGER.info("Method annotation: {}", annotation);
        }
    }
}
