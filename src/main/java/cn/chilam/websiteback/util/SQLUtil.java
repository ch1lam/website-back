package cn.chilam.websiteback.util;

/**
 * @program: website-back
 * @description: 数据库工具
 * @author: chilam
 * @create: 2020-05-11 12:45
 **/
public class SQLUtil {
    /**
     * @description: 用于检查Update，Delete等SQL语句是否产生了影响，没产生影响时将抛出异常
     * @author: chilam
     * @param: rows 影响的行数
     * @date: 2020-05-11
     */
    public static void checkEffective(int rows) {
        if (rows <= 0) throw new IllegalArgumentException();
    }
}
