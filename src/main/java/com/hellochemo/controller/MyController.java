package com.hellochemo.controller;

import com.hellochemo.MainConfig.MainConfig;
import com.hellochemo.bean.MyBean;
import com.hellochemo.bean.TictactoeBean;
import com.hellochemo.service.MyBeanService;
import com.hellochemo.service.TictactoeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @GetMapping(value="/getDetails")
    public ResponseEntity<tictactoeOutput> getDetails(@RequestParam Integer Id, @RequestParam String upass) {
        TictactoeService tictactoeService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        tictactoeService = (TictactoeService) applicationContext.getBean("tictactoeServiceImpl");
        TictactoeBean bean = getTictactoeDetails(tictactoeService,Id);
        Boolean b = false;
        tictactoeOutput out = null;
        if(bean!=null && bean.getuPass().equals(upass)) {
            b = true;
            out = new tictactoeOutput(Id, bean.getName(), "*******", b,bean.getSession(),bean.getPoints(),bean.getHighScore());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Access-Control-Allow-Origin",
                    "*");
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(out);
        }
        out = new tictactoeOutput(Id, "user/password was wrong", "*******", false, 0, 0, 0);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(out);
    }

    @GetMapping(value="/getOpponentDetails")
    public ResponseEntity<OpponentOutput> getOpponentDetails(@RequestParam Integer Id) {
        TictactoeService tictactoeService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        tictactoeService = (TictactoeService) applicationContext.getBean("tictactoeServiceImpl");
        TictactoeBean bean = getTictactoeDetails(tictactoeService,Id);
        Boolean b = false;
        OpponentOutput out = null;
        if(bean!=null) {
            b = true;
            out = new OpponentOutput(Id, bean.getName(), b,bean.getSession(),bean.getPoints(),bean.getHighScore());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Access-Control-Allow-Origin",
                    "*");
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(out);
        }
        out = new OpponentOutput(Id, "user/password was wrong", false, 0, 0, 0);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(out);
    }

    @GetMapping(value="/setHighScore")
    public Boolean setHighScore(@RequestParam Integer Id, @RequestParam Integer highScore) throws Exception {
        TictactoeService tictactoeService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        tictactoeService = (TictactoeService) applicationContext.getBean("tictactoeServiceImpl");
        return tictactoeService.setHighScore(Id, highScore);
    }

    @GetMapping(value="/setPoints")
    public ResponseEntity<Boolean> setPoints(@RequestParam Integer Id, @RequestParam Integer points) throws Exception {
        TictactoeService tictactoeService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        tictactoeService = (TictactoeService) applicationContext.getBean("tictactoeServiceImpl");
        System.out.println("done for " + Id + " and " + points );
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(tictactoeService.setPoints(Id, points));
    }

    @GetMapping(value="/setDetails")
    public ResponseEntity<TictactoeBean> setDetails(@RequestParam String name, @RequestParam String password) throws Exception {
        TictactoeService tictactoeService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        tictactoeService = (TictactoeService) applicationContext.getBean("tictactoeServiceImpl");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin",
                "*");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(tictactoeService.setDetails(name, password));
    }

    @GetMapping(value="/login")
    public Output Login(@RequestParam Integer id, @RequestParam String upass) {
            MyBeanService myBeanService=null;
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
            myBeanService = (MyBeanService) applicationContext.getBean("myBeanServiceImpl");
            MyBean name = getEmployeeDetails(myBeanService,id);
            Boolean b = false;
            Output out = null;
            if(name!=null && name.getUpass().equals(upass)) {
                b = true;
                out = new Output(id, name.getName(), "*******", b);
                return out;
            }
            out = new Output(id, "user/password was wrong", "*******", false);
            return out;
    }

    @GetMapping(value="/logon")
    public String Logon(@RequestParam String uname, @RequestParam String upass) {
        MyBeanService myBeanService=null;
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        myBeanService = (MyBeanService) applicationContext.getBean("myBeanServiceImpl");

        int id = addUser(myBeanService, uname, upass);
        if(id!=404) {
            return "Login Succesfull with id " + id;
        }
        return "logon failed xD";
    }
    public static Integer addUser(MyBeanService serviceImpl, String uname, String upass) {

        MyBean bean = new MyBean();
        bean.setName(uname);
        bean.setUpass(upass);
        int id = 404;
        try {
            id = serviceImpl.addUser(bean);
            System.out.println("Employee Registered Successfully: " + id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public static MyBean getEmployeeDetails(MyBeanService serviceImpl, Integer id) {

        try {

            MyBean employeeBean = serviceImpl.getUserDetails(id);

            if (employeeBean == null)
            {
                return null;
            }
            else
            {
                System.out.println("LDAP was called for User details");
                return employeeBean;
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static TictactoeBean getTictactoeDetails(TictactoeService serviceImpl, Integer id) {

        try {

            TictactoeBean employeeBean = serviceImpl.getUserDetails(id);

            if (employeeBean == null)
            {
                return null;
            }
            else
            {
                System.out.println("LDAP was called for User details");
                return employeeBean;
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
        return null;
    }
}
