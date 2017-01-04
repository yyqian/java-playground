package com.yyqian.playground.mybatis.demo;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2017-01-04T13:25:06+08:00.
 *
 * @author Yinyin Qian
 */
public class JavassistDemo {
    public static void main(String[] args) throws CannotCompileException, IllegalAccessException, NotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        demo1();
    }

    private static void demo1() throws CannotCompileException, InvocationTargetException, IllegalAccessException, NotFoundException, InstantiationException, NoSuchMethodException {
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("foo.Student");
        StringBuffer body = null;
        // 参数 1：属性类型 2：属性名称 3：所属类CtClass
        CtField ctField = new CtField(cp.get("java.lang.String"), "name",
                ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        // 设置name属性的get set方法
        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));
        ctClass.addField(ctField, CtField.Initializer.constant("default"));

        // 参数 1：参数类型 2：所属类CtClass
        CtConstructor ctConstructor = new CtConstructor(new CtClass[] {},
                ctClass);
        body = new StringBuffer();
        body.append("{\n name=\"me123\";\n}");
        ctConstructor.setBody(body.toString());
        ctClass.addConstructor(ctConstructor);

        // 参数： 1：返回类型 2：方法名称 3：传入参数类型 4：所属类CtClass
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute",
                new CtClass[] {}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        body = new StringBuffer();
        body.append("{\n System.out.println(name);");
        body.append("\n System.out.println(\"execute ok\");");
        body.append("\n return ;");
        body.append("\n}");
        ctMethod.setBody(body.toString());
        ctClass.addMethod(ctMethod);
        Class<?> c = ctClass.toClass();
        Object o = c.newInstance();
        Method method = o.getClass().getMethod("execute", new Class[] {});
        // 调用字节码生成类的execute方法
        method.invoke(o, new Object[] {});
    }
}
