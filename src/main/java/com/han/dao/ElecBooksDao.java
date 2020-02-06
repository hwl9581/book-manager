package com.han.dao;

import com.han.entity.ElecBooks;
import com.han.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ElecBooksDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 19:28
 */
public class ElecBooksDao {

    public int insert(ElecBooks elecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "insert into elec_books(elec_book_name, elec_book_author, elec_book_address, create_time) values(?,?,?,?)";
        Object[] params = {elecBooks.getElec_book_name(), elecBooks.getElec_book_author(), elecBooks.getElec_book_address(), elecBooks.getCreate_time()};
        int result = qr.update(sql, params);
        return result;
    }

    public List<ElecBooks> selectAll(ElecBooks elecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        ArrayList<Object> params = new ArrayList<>();
        String sql = "select * from elec_books where 1 = 1";
        if (elecBooks.getId() != null){
            sql += " and  id = ?";
            params.add(elecBooks.getId());
        }
        if (elecBooks.getElec_book_name() != null){
            sql += " and  elec_book_name = ?";
            params.add(elecBooks.getElec_book_name());
        }
        if (elecBooks.getElec_book_author() != null){
            sql += " and  elec_book_author = ?";
            params.add(elecBooks.getElec_book_author());
        }
        if (elecBooks.getElec_book_address() != null){
            sql += " and  elec_book_address = ?";
            params.add(elecBooks.getElec_book_address());
        }
        if (elecBooks.getCreate_time() != null){
            sql += " and  create_time = ?";
            params.add(elecBooks.getCreate_time());
        }
        List<ElecBooks> list = qr.query(sql, new BeanListHandler<>(ElecBooks.class), params.toArray());
        return list;
    }

    public ElecBooks selectOne(ElecBooks elecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        ArrayList<Object> params = new ArrayList<>();
        String sql = "select * from elec_books where 1 = 1";
        if (elecBooks.getId() != null){
            sql += " and  id = ?";
            params.add(elecBooks.getId());
        }
        if (elecBooks.getElec_book_name() != null){
            sql += " and  elec_book_name = ?";
            params.add(elecBooks.getElec_book_name());
        }
        if (elecBooks.getElec_book_author() != null){
            sql += " and  elec_book_author = ?";
            params.add(elecBooks.getElec_book_author());
        }
        if (elecBooks.getElec_book_address() != null){
            sql += " and  elec_book_address = ?";
            params.add(elecBooks.getElec_book_address());
        }
        if (elecBooks.getCreate_time() != null){
            sql += " and  create_time = ?";
            params.add(elecBooks.getCreate_time());
        }
        ElecBooks elecBooks1 = qr.query(sql, new BeanHandler<>(ElecBooks.class), params.toArray());
        return elecBooks1;
    }
}
