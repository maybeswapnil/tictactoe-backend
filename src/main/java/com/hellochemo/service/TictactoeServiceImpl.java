package com.hellochemo.service;

import com.hellochemo.bean.TictactoeBean;
import com.hellochemo.dao.TictactoeDAO;
import com.hellochemo.exceptions.InvalidUpdateOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TictactoeServiceImpl implements TictactoeService {

    @Autowired
    private TictactoeDAO tictactoeDAO;

    public Integer addUser(TictactoeBean tictactoeBean) throws Exception {
        return 1001;
    }

    public TictactoeBean getUserDetails(Integer Id) throws Exception {
        TictactoeBean bean =tictactoeDAO.getUserDetails(Id);
        if(bean==null) {
            throw new InvalidUpdateOperationException();
        }
        return bean;
    }

    public Boolean setHighScore(Integer Id, Integer highScore) throws Exception {
        return tictactoeDAO.setHighScore(Id, highScore);
    }

    public Boolean setPoints(Integer Id, Integer points) throws Exception {
        return tictactoeDAO.setPoints(Id, points);
    }
}
