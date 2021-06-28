package com.hellochemo.dao;

import com.hellochemo.bean.TictactoeBean;
import com.hellochemo.entity.TictactoeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@SuppressWarnings("unchecked")
@Transactional(value="txManager")
public class TictactoeDAOImpl implements TictactoeDAO{

    @PersistenceContext
    EntityManager entityManager;

    public Integer addUser(TictactoeBean myBean) throws Exception{
        // TODO Auto-generated method stub
        Integer Id = 0;

        TictactoeEntity tictactoeEntityBean =convertBeanToEntity(myBean);
        try {
            entityManager.persist(tictactoeEntityBean);

            Id = tictactoeEntityBean.getId();
        } catch (Exception exception) {
            throw exception;
        }
        return Id;
    }

    public TictactoeBean getUserDetails(Integer Id) throws Exception{
        // TODO Auto-generated method stub
        TictactoeBean tictactoeBean = null;
        try {
            TictactoeEntity tictactoeEntity = (TictactoeEntity) entityManager.find(TictactoeEntity.class, Id);

            if(tictactoeEntity!=null){
                tictactoeBean=convertEntityToBean(tictactoeEntity);
            }

        } catch (Exception exception) {

            throw exception;
        }

        return tictactoeBean;
    }

    public Boolean setHighScore(Integer Id, Integer highScore) throws Exception {
        TictactoeBean tictactoeBean = null;
        try {
            TictactoeEntity tictactoeEntity = (TictactoeEntity) entityManager.find(TictactoeEntity.class, Id);
            tictactoeEntity.setHighScore(highScore);
            entityManager.persist(tictactoeEntity);
            if(tictactoeEntity!=null){
                tictactoeBean=convertEntityToBean(tictactoeEntity);
            }

        } catch (Exception exception) {

            throw exception;
        }

        if(tictactoeBean.getHighScore()==highScore) {
            return true;
        }
        return false;
    }

    public Boolean setPoints(Integer Id, Integer points) throws Exception {
        TictactoeBean tictactoeBean = null;
        try {
            TictactoeEntity tictactoeEntity = (TictactoeEntity) entityManager.find(TictactoeEntity.class, Id);
            tictactoeEntity.setPoints(points);
            entityManager.persist(tictactoeEntity);
            if(tictactoeEntity!=null){
                tictactoeBean=convertEntityToBean(tictactoeEntity);
            }

        } catch (Exception exception) {

            throw exception;
        }

        if(tictactoeBean.getPoints()==points) {
            return true;
        }
        return false;
    }

    public TictactoeBean setDetails(String name, String password) throws Exception {
        TictactoeBean tictactoeBean = null;
        try {
            TictactoeBean bean = new TictactoeBean();
            bean.setName(name);
            bean.setuPass(password);
            bean.setPoints(0);
            bean.setHighScore(0);
            bean.setSession(0);
            TictactoeEntity tictactoeEntity = convertBeanToEntity(bean);
            entityManager.persist(tictactoeEntity);
            if(tictactoeEntity!=null){
                tictactoeBean=convertEntityToBean(tictactoeEntity);
                return tictactoeBean;
            }

            return bean;

        } catch (Exception exception) {

            throw exception;
        }
    }


    public static TictactoeBean convertEntityToBean(TictactoeEntity entity){
        TictactoeBean bean = new TictactoeBean();
        BeanUtils.copyProperties(entity, bean);
        return bean;
    }
    public static TictactoeEntity convertBeanToEntity(TictactoeBean bean){
        TictactoeEntity entity = new TictactoeEntity();
        BeanUtils.copyProperties(bean,entity);
        return entity;
    }
}
