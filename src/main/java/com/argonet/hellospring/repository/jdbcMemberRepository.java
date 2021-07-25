package com.argonet.hellospring.repository;

import com.argonet.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class jdbcMemberRepository implements MemberRepository {
     private final  DataSource dataSource;

    public jdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;


    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;

        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,member.getName());

            preparedStatement.executeUpdate();
            rs= preparedStatement.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("Id listing failed");
            }

            return member;


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            close(connection,preparedStatement,rs);
        }

        return null;
    }
    private void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            if(pstmt!=null){
                pstmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id=?";

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;

        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            rs=preparedStatement.executeQuery();

            if(rs.next()){
                Member member= new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);

            }else{
                return Optional.empty();
            }



        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            close(connection,preparedStatement,rs);
        }

        return null;

    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name=(?)";

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;

        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);


            rs=preparedStatement.executeQuery();
            if(rs.next()){

                Member member= new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));

                return Optional.of(member);

            }else{
                return Optional.empty();
            }



        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            close(connection,preparedStatement,rs);
        }

        return null;

    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet rs=null;

        try {
            connection=getConnection();
            preparedStatement=connection.prepareStatement(sql);

            rs=preparedStatement.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member= new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);

            }
            return members;



        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            close(connection,preparedStatement,rs);
        }

        return null;
    }
    public void clearStore(){

    }
}
