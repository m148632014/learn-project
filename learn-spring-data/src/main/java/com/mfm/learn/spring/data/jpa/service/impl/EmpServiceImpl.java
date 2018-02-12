package com.mfm.learn.spring.data.jpa.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfm.learn.spring.data.jpa.entity.Dept;
import com.mfm.learn.spring.data.jpa.entity.Emp;
import com.mfm.learn.spring.data.jpa.entity.dto.EmpDeptDto;
import com.mfm.learn.spring.data.jpa.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EntityManager em;

    public List<EmpDeptDto> q1() {
        // usually
        List<Object[]> results = this.em.createNativeQuery(
            "select e.emp_no, e.ename, e.job from emp e").getResultList();

        results.stream().forEach((record) -> {
            Integer empNo = (Integer) record[0];
            String ename = (String) record[1];
            String job = (String) record[2];
            System.out.println(empNo + "," + ename + "," + job);
        });

        //use default Mapping
        List<Emp> emps = this.em
            .createNativeQuery(
                "SELECT e.emp_no, e.ename, e.job,e.mgr,e.hire_date,e.sal,e.comm,e.dept_no FROM emp e",
                Emp.class).getResultList();
        emps.stream().forEach(System.out::println);

        //use @SqlResultSetMapping+@NamedNativeQuery+@EntityResult
        List<Emp> emps2 = this.em.createNamedQuery("empquery").getResultList();
        emps2.stream().forEach(System.out::println);

        //use @SqlResultSetMapping+@NamedNativeQuery+@ConstructorResult
        List<EmpDeptDto> dtos = this.em.createNamedQuery("Emp.empDtoQuery")
            .getResultList();
        dtos.stream().forEach(System.out::println);

        //use  Hibernate specific features ResultTransformer+alias
        Session session = (Session) this.em.getDelegate();
        //nativeSQL+addEntity1
        List<Object[]> results2 = session
            .createSQLQuery(
                "SELECT {e.*},{d.*} FROM emp e,dept d where d.dept_no=e.dept_no")
            .addEntity("e", Emp.class).addEntity("d", Dept.class).list();
        results2.stream().forEach(result -> {
            Emp e = (Emp) result[0];
            Dept d = (Dept) result[1];
            System.out.println(e + "====" + d);
        });

        //nativeSQL+addEntity2
        List<Emp> results2_1 = session.createSQLQuery("SELECT * FROM emp e")
            .addEntity(Emp.class).list();
        results2_1.stream().forEach(System.out::println);
        //nativeSQL+addEntity3 默认仍然查询所有字段
        List<Emp> results2_2 = session
            .createSQLQuery("SELECT e.emp_no,e.ename,e.job FROM emp e")
            .addEntity(Emp.class).list();
        results2_2.stream().forEach(System.out::println);
        List<Object[]> results2_3 = session
            .createSQLQuery("SELECT * FROM emp e").addScalar("emp_no")
            .addScalar("ename").addScalar("job").addEntity(Emp.class).list();
        System.out.println(results2_3.size() + "size");
        results2_3.stream().forEach(
        //o.lenth is 4所以包含以下内容
        //0 1 2 3 : emp_no ename job emp
            o -> {
                System.out.println(o.length + "==" + o[0] + "," + o[1] + ","
                    + o[2] + "=" + o[3]);
            });
        //nativeSQL+addEntity+addScalar
        List<Object[]> results3 = session
            .createSQLQuery(
                "SELECT {d.*},count(e.emp_no) as empCount FROM emp e,dept d where d.dept_no=e.dept_no group by d.dept_no")
            .addEntity("d", Dept.class)
            .addScalar("empCount", StandardBasicTypes.LONG).list();
        results3.stream().forEach(result -> {
            Dept dept = (Dept) result[0];
            Long empCount = (Long) result[1];
            System.out.println(dept + "-" + empCount);
        });

        //nativeSQL+ResultTransformer+POJOs
        List<EmpDeptDto> dtos2 = session
            .createSQLQuery(
                "SELECT e.emp_no as empNo, e.ename,d.dname as departmentName FROM emp e,dept d where d.dept_no=e.dept_no")
            .setResultTransformer(Transformers.aliasToBean(EmpDeptDto.class))
            .list();
        dtos2.stream().forEach(System.out::println);
        return dtos2;
    }

    public List<EmpDeptDto> q2() {
        // usually it's works.
//        List<Object[]> results = this.em.createQuery(
//            "select e.empNo, e.ename, e.job from Emp e").getResultList();
//
//        results.stream().forEach((record) -> {
//            Integer empNo = (Integer) record[0];
//            String ename = (String) record[1];
//            String job = (String) record[2];
//            System.out.println(empNo + "," + ename + "," + job);
//        });

        //use default Mapping it's works.
//        List<Emp> emps = this.em.createQuery("from Emp").getResultList();
//        emps.stream().forEach(System.out::println);

        //use @SqlResultSetMapping+@NamedQuery+@EntityResult
//        List<Emp> emps2 = this.em.createQuery("empqueryJPQL").getResultList();
//        emps2.stream().forEach(System.out::println);
//
//        //use @SqlResultSetMapping+@NamedQuery+@ConstructorResult
//        List<EmpDeptDto> dtos = this.em.createNamedQuery("empDtoqueryJPQL")
//            .getResultList();
//        dtos.stream().forEach(System.out::println);

        //use  Hibernate specific features ResultTransformer+alias
        Session session = (Session) this.em.getDelegate();
        //JPQL it's works.
        List<Object[]> results2 = session.createQuery(
            "SELECT e,d FROM Emp e,Dept d where d.deptNo=e.deptNo").list();
        results2.stream().forEach(result -> {
            Emp e = (Emp) result[0];
            Dept d = (Dept) result[1];
            System.out.println(e + "====" + d);
        });

        //JPQL+addEntity2
//        List<Emp> results2_1 = session.createQuery("FROM Emp").list();
//        results2_1.stream().forEach(System.out::println);
        //nativeSQL+addEntity3 默认仍然查询所有字段
//        List<Emp> results2_2 = session
//            .createSQLQuery("SELECT e.emp_no,e.ename,e.job FROM emp e")
//            .addEntity(Emp.class).list();
//        results2_2.stream().forEach(System.out::println);
        //addScarlar addEntity can not use on JPQL Query
//        List<Object[]> results2_3 = session.createQuery("SELECT e FROM Emp e")
//            .list();
//        System.out.println(results2_3.size() + "size");
//        results2_3.stream().forEach(
//        //o.lenth is 4所以包含以下内容
//        //0 1 2 3 : emp_no ename job emp
//            o -> {
//                System.out.println(o.length + "==" + o[0] + "," + o[1] + ","
//                    + o[2] + "=" + o[3]);
//            });
        //JPQL it's works.
        List<Object[]> results3 = session
            .createQuery(
                "SELECT d,count(e.empNo) as empCount FROM Emp e,Dept d where d.deptNo=e.deptNo group by d.deptNo")
            .list();
        results3.stream().forEach(result -> {
            Dept dept = (Dept) result[0];
            Long empCount = (Long) result[1];
            System.out.println(dept + "-" + empCount);
        });

        //JPQL+ResultTransformer+POJOs 必须起别名，不然不会设置值
        List<EmpDeptDto> dtos2 = session
            .createQuery(
                "SELECT e.empNo as empNo, e.ename as ename,d.dname as departmentName FROM Emp e,Dept d where d.deptNo=e.deptNo")
            .setResultTransformer(Transformers.aliasToBean(EmpDeptDto.class))
            .list();
        dtos2.stream().forEach(System.out::println);
        return dtos2;
    }

    public List<EmpDeptDto> getEmpDtoByHibernateHQL() {
        //use  Hibernate specific features ResultTransformer+alias
        Session session = (Session) this.em.getDelegate();
        //JPQL+ResultTransformer+POJOs 必须起别名，不然不会设置值
        List<EmpDeptDto> dtos = session
            .createQuery(
                "SELECT e.empNo as empNo, e.ename as ename,d.dname as departmentName FROM Emp e,Dept d where d.deptNo=e.deptNo")
            .setResultTransformer(Transformers.aliasToBean(EmpDeptDto.class))
            .list();
        return dtos;
    }

    public List<EmpDeptDto> getEmpDtoByHibernateSQL() {
        //use  Hibernate specific features ResultTransformer+alias
        Session session = (Session) this.em.getDelegate();
        //nativeSQL+ResultTransformer+POJOs
        List<EmpDeptDto> dtos = session
            .createSQLQuery(
                "SELECT e.emp_no as empNo, e.ename,d.dname as departmentName FROM emp e,dept d where d.dept_no=e.dept_no")
            .setResultTransformer(Transformers.aliasToBean(EmpDeptDto.class))
            .list();
        return dtos;
    }
}
