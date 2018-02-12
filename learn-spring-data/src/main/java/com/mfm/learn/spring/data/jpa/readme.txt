Spring-data-jpa学习
1.使用默认API查询
JpaRepository extends PagingAndSortingRepository, QueryByExampleExecutor
拥有很多默认的方法。支持分页，排序，count，exsits,Example（按实体属性条件查询单个或多个），删除，批量删除，单个查询，查询单个等方法

2.使用派生法查询
Spring-data 提供了根据方法名称查询 使用约定好的关键字，可以进行各种条件查询。

3.自定义查询（JPQL/SQL)均可
支持JPA的@NamedQuery（JPQL）和@NamedNativeQuery(原生SQL)
支持JPA的@Query查询，默认使用JPQL (navtive=true，表示使用原生SQL）

4.结果映射ResultMapping
大多情况下查询出来的都是一个List<Object[]>
使用@SqlResultSetMappings可以控制，但是比较繁琐。
使用hibernate特性，可以简化，底层使用了hiberate可以考虑优先使用。
使用投影由于投影是接口，所以个人觉得适用于只读场景

应用：join查询不同列的属性到Dto
1.spring-data projection投影
2.@SqlResultSetMappings映射结果
3.使用hibernate特性查询
