package com.hellochemo.dao;

import com.hellochemo.bean.TictactoeBean;

public interface TictactoeDAO {
    Integer addUser(TictactoeBean myBean) throws Exception;

    TictactoeBean getUserDetails(Integer Id) throws Exception;

    Boolean setHighScore(Integer Id, Integer highScore) throws Exception;

    Boolean setPoints(Integer Id, Integer points) throws Exception;

    TictactoeBean setDetails(String name, String password) throws Exception;
}
