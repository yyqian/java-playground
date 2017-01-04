package com.yyqian.playground.mybatis.demo;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.yyqian.playground.mybatis.util.ByteCodeUtil;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.sql.DataSource;

/**
 * Created on 2017-01-04T10:32:08+08:00.
 *
 * @author Yinyin Qian
 */
public class BuildDatasource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildDatasource.class);

    public static void main(String[] args) throws CannotCompileException, NotFoundException, NoSuchMethodException, IOException {
        DataSource dataSource = configMysqlDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("runtimeMySQL", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        Class<?> clazz = ByteCodeUtil.genGenericClass();
        configuration.addMapper(clazz);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.select("mybatis.GenMapper.selectAll", new MapHandler());
        }
    }

    private static DataSource configMysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        // prepare url start
        mysqlDataSource.setServerName("192.168.6.231");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        // prepare url end
        mysqlDataSource.setPassword("PatSnap2017");
        mysqlDataSource.setDatabaseName("test");
        return mysqlDataSource;
    }


    private static Class<?> genClass() throws CannotCompileException, NotFoundException, IOException {
        String sql = "select * from user where id=1";
        String methodName = "selectOne";
        String methodReturn = "java.util.Map";
        ClassPool pool = ClassPool.getDefault();
        CtClass ctList = pool.get(methodReturn);
        CtClass targetClass = pool.makeInterface("mybatis.GenMapper");
        //annotation
        ClassFile classFile = targetClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation("org.apache.ibatis.annotations.Select", constPool);
        ArrayMemberValue annoValue = new ArrayMemberValue(constPool);
        MemberValue value = new StringMemberValue(sql, constPool);
        annoValue.setValue(new MemberValue[]{value});
        annotation.addMemberValue("value", annoValue);
        attr.addAnnotation(annotation);
        CtMethod ctMethod = CtNewMethod.abstractMethod(ctList,
                methodName,
                null,
                null,
                targetClass);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        methodInfo.addAttribute(attr);
        SignatureAttribute signatureAttribute = new SignatureAttribute(methodInfo.getConstPool(), "");
        methodInfo.addAttribute(signatureAttribute);
        targetClass.addMethod(ctMethod);
        return targetClass.toClass();
    }

}
