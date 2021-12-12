package per.poacher.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import per.poacher.pojo.Book;

import java.util.List;

/**
 * @author poacher
 * @create 2021-05-02-15:22
 */
@Repository
public interface BookMapper {

    /**
     * 添加图书
     * @param book 待加入的图书
     * @return 返回1表示添加成功
     */
    @Insert("insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) " +
            "values(#{name},#{author},#{price},#{sales},#{stock},#{imgPath})")
    int addBook(Book book);

    /**
     * 根据图书id进行删除图书
     * @param id 待删除的图书id
     * @return 返回1表示添加成功
     */
    @Delete("delete from t_book where id = #{id}")
    int deleteBook(int id);

    /**
     * 根据图书的id修改图书信息
     * @param book 待修改的图书
     * @return 返回1表示修改成功
     */
    int updateBook(Book book);

    /**
     * 查询一条图书信息
     * @param id 需要查询的图书的id
     * @return 返回图书信息
     */
    @Select("select * from t_book where id = #{id}")
    Book queryOne(int id);

    /**
     * 查询多条图书信息
     * @return 返回图书的集合
     */
    @Select("select * from t_book")
    List<Book> queryMore();

    /**
     * 根据价格区间获取图书的具体信息
     * @param min 最低价格
     * @param max 最高价格
     * @return 返回图书信息
     */
    @Select("select * from t_book where price between #{min} and #{max}")
    List<Book> queryBooksByPrice(@Param("min") int min, @Param("max") int max);
}
