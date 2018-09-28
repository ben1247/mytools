package org.zy.mytools.dao;

import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuezhang on 18/9/24.
 */
public class PayStatementDao {

    public PayStatement getPayStatement(String statementId, String transactionId){
        String baseSql = "select statement_id,transaction_Id,user_name from pay_statement where statement_id = '%s' and transaction_Id = '%s'";
        String sql = String.format(baseSql,statementId,transactionId);

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        PayStatement payStatement = null;
        try {
            conn = DBConnect.getConnection();
            stat = DBConnect.getStatement(conn);
            rs = DBConnect.getResultSet(stat,sql);
            // 输出查询结果
            while(rs.next()){
                payStatement = new PayStatement();
                payStatement.setStatementId(rs.getString("statement_id"));
                payStatement.setTransactionId(rs.getString("transaction_Id"));
                payStatement.setUserName(rs.getString("user_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,stat,rs);
        }

        return payStatement;

    }

    public PayStatement getPayStatementByOutOrderNo(String outTradeNo){
        String baseSql = "select statement_id,transaction_Id,user_name from pay_statement where outTrade_no = '%s' ";
        String sql = String.format(baseSql,outTradeNo);

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        PayStatement payStatement = null;
        try {
            conn = DBConnect.getConnection();
            stat = DBConnect.getStatement(conn);
            rs = DBConnect.getResultSet(stat,sql);
            // 输出查询结果
            while(rs.next()){
                payStatement = new PayStatement();
                payStatement.setStatementId(rs.getString("statement_id"));
                payStatement.setTransactionId(rs.getString("transaction_Id"));
                payStatement.setUserName(rs.getString("user_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,stat,rs);
        }

        return payStatement;
    }

    public List<PayStatement> getPayStatementList(
            String merchantId , String payWays ,
            String startTime , String endTime ,
            int pageNo , int pageSize){
        String baseSql = "select statement_id,transaction_Id,user_name,amount,subject from pay_statement where merchant_Id = '%s' and pay_state = 3 and create_time >= '%s' and create_time <= '%s' and pay_way in (%s) order by id asc limit %s , %s";
        int offset = (pageNo - 1) * pageSize;
        String sql = String.format(baseSql,merchantId,startTime,endTime,payWays,offset,pageSize);
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        List<PayStatement> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            stat = DBConnect.getStatement(conn);
            rs = DBConnect.getResultSet(stat,sql);
            // 输出查询结果
            while(rs.next()){
                PayStatement payStatement = new PayStatement();
                payStatement.setStatementId(rs.getString("statement_id"));
                payStatement.setTransactionId(rs.getString("transaction_Id"));
                payStatement.setUserName(rs.getString("user_name"));
                payStatement.setAmount(rs.getString("amount"));
                payStatement.setSubject(rs.getString("subject"));
                list.add(payStatement);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnect.closeAll(conn,stat,rs);
        }
        return list;
    }

}
