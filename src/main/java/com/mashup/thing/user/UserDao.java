package com.mashup.thing.user;


import com.mashup.thing.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplateObject;

    public UserDao (JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public User findUser(String uid) {

        try{
            return this.jdbcTemplateObject.queryForObject("SELECT uid, date_birth, gender,nick_name  FROM user where uid = ?",
                    (rs,rowNum) -> new User(rs.getString("uid"), rs.getInt("date_birth"),rs.getString("nick_name"),rs.getString("gender")),
                    uid);
        }catch (EmptyResultDataAccessException e) {
            throw new BaseException(HttpStatus.BAD_REQUEST);
        }

    }
    // 유저정보수정
    public int updateUser(String uid ,String nickName, String gender, int dateBirth ,String imageUrl) {
      String SQL = "UPDATE user SET nick_name = ?,date_birth= ?,gender = ? ,image_url = ? WHERE uid = ?";
        return jdbcTemplateObject.update(SQL,new Object[]{nickName,dateBirth,gender,imageUrl,uid});

    }

    //uid 인증
    public String validateUid (String uid){

        try{
            String SQL = "SELECT uid  FROM user WHERE uid = ?";
            return jdbcTemplateObject.queryForObject(SQL, new Object[]{uid}, String.class);

        } catch (EmptyResultDataAccessException e) {
            throw new BaseException(HttpStatus.BAD_REQUEST);
        }

        }

    public String validateNickname(String nickName){

        String SQL = "SELECT nick_name  FROM user WHERE nick_name = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{nickName}, String.class);

        }
    }
