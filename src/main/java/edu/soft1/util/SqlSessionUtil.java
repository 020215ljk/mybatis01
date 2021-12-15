package edu.soft1.util;
import edu.soft1.dao.CustomersDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;//重量级对象
    private static final ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();//本地线程
    static {//静态块
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
            new RuntimeException("读取配置文件失败！");
        }
    }

    /**
     * 打开SqlSession对象
     * @return
     */
    public static SqlSession openSqlSession(){
        SqlSession sqlSession = tl.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            tl.set(sqlSession);
        }
        return sqlSession;
    }
    /**
     * 关闭SqlSession对象
     */
    public static void closeSqlSession(){
        SqlSession sqlSession = openSqlSession();
        sqlSession.close();
    }
    /**
     * 提交事务
     */
    public static void commit(){
        SqlSession sqlSession = openSqlSession();
        sqlSession.commit();
        closeSqlSession();
    }
    /**
     * 回滚事务
     */
    public static void rollback(){
        SqlSession sqlSession = openSqlSession();
        sqlSession.rollback();
        closeSqlSession();
    }

    /**
     * CustomersDao customersDao  = (CustomersDao) SqlSessionUtil.getMapper(CustomersDao.class)
     * GoodsDao goodsDao  = (GoodsDao) SqlSessionUtil.getMapper(GoodsDao.class)
     * @param clazz
     * @return
     */
    public static Object getMapper(Class clazz){
        return openSqlSession().getMapper(clazz);
    }
}