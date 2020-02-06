package com.han.dao;

import com.han.entity.BuyElecBooks;
import com.han.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BuyElecBooksDao
 * @Description TODO
 * @Author HanWL
 * @Since 2020/2/5 0005 21:48
 */
public class BuyElecBooksDao {

    public int insert(BuyElecBooks buyElecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "insert into buy_elec_books(user_id, elec_book_id) values(?,?)";
        Object[]  params = {buyElecBooks.getUser_id(), buyElecBooks.getElec_book_id()};
        int result = qr.update(sql, params);
        return result;
    }

    public List<BuyElecBooks> selectAll(BuyElecBooks buyElecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from buy_elec_books where 1 = 1";
        ArrayList<Object> params = new ArrayList<>();
        if (buyElecBooks.getId() != null){
            sql += " and id = ?";
            params.add(buyElecBooks.getId());
        }
        if (buyElecBooks.getUser_id() != null){
            sql += " and user_id = ?";
            params.add(buyElecBooks.getUser_id());
        }
        if (buyElecBooks.getElec_book_id() != null){
            sql += " and elec_book_id = ?";
            params.add(buyElecBooks.getElec_book_id());
        }
        List<BuyElecBooks> list = qr.query(sql, new BeanListHandler<>(BuyElecBooks.class), params.toArray());
        return list;
    }

    public BuyElecBooks selectOne(BuyElecBooks buyElecBooks) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from buy_elec_books where 1 = 1";
        ArrayList<Object> params = new ArrayList<>();
        if (buyElecBooks.getId() != null){
            sql += "id = ?";
            params.add(buyElecBooks.getId());
        }
        if (buyElecBooks.getUser_id() != null){
            sql += "user_id = ?";
            params.add(buyElecBooks.getUser_id());
        }
        if (buyElecBooks.getElec_book_id() != null){
            sql += "elec_book_id = ?";
            params.add(buyElecBooks.getElec_book_id());
        }
        BuyElecBooks buyElecBooks1 = qr.query(sql, new BeanHandler<>(BuyElecBooks.class), params.toArray());
        return buyElecBooks1;
    }

}
