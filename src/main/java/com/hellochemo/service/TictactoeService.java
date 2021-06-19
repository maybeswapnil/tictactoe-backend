package com.hellochemo.service;

import com.hellochemo.bean.TictactoeBean;

public interface TictactoeService {
    Integer addUser(TictactoeBean tictactoeBean) throws Exception;

    TictactoeBean getUserDetails(Integer Id) throws Exception;

    Boolean setHighScore(Integer Id, Integer highScore) throws Exception;

    Boolean setPoints(Integer Id, Integer points) throws Exception;
}
