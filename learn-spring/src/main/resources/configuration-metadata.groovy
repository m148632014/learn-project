import org.apache.commons.dbcp2.BasicDataSource;

beans {
    dataSource(BasicDataSource) {
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://10.255.33.108:3306/keplerdb-1"
        username = "root"
        password = "123456"
    }
}