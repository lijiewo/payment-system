package com.software2019.paysys.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.software2019.paysys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Controller
public class UserController {

    @RequestMapping("login")
    @ResponseBody
    public boolean login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        if(userService.login(username,password)){
            session.setAttribute("user",username);
            System.out.println("success");
            return true;
        }
        else { System.out.println("wrongPass"); return false;}

    }

    @RequestMapping("register")
    @ResponseBody
    public boolean register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+password);
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        if(userService.Register(username,password)){
            System.out.println("success");
            return true;
        }
        else { System.out.println("wrongRegister"); return false;}

    }


    @RequestMapping("online")
    @ResponseBody
    public String online(HttpServletRequest request,HttpServletResponse response){
        String username = request.getSession().getAttribute("user").toString();
        if(username!=null){
            return username;
        }
        else return "未登录";
    }

    @RequestMapping("logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        return true;

    }


    @RequestMapping("transfer")
    @ResponseBody
    public boolean transfer(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String receipient = request.getParameter("receipient");
        String affordname=request.getSession().getAttribute("user").toString();
        if(affordname==null)return false;
        BigInteger money =  new BigInteger(request.getParameter("money"));
        UserService userService = new UserService();
        if(userService.transfer(affordname,receipient,money)){
            System.out.println("tranferSucess!");
            return true;
        }
        else return false;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public boolean updatePassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String newPwd = request.getParameter("newPwd");
        String oldPwd =  request.getParameter("oldPwd");
        String username=request.getSession().getAttribute("user").toString();
        if(username==null)return false;
        UserService userService = new UserService();
        if(userService.updatePassword(username, newPwd,oldPwd)){
            System.out.println("updatePasswordSucess!");
            return true;
        }
        else return false;
    }

    @RequestMapping("allSearchTran")
    @ResponseBody
    public JSONArray allSearchTran(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UserService userService = new UserService();
        String username=request.getSession().getAttribute("user").toString();
        if(username==null)return null;
        BigInteger level=userService.returnLevel(username);
        int total=new Integer(String.valueOf(userService.getNumOfMsg()));
        JSONArray js = new JSONArray();
        if(level.compareTo(new BigInteger("1"))==0){
            for(int i=0;i<total;i++){
                    JSONObject json = new JSONObject();
                    json.put("Afford", userService.returnAfford(BigInteger.valueOf(i)));
                    json.put("Recv", userService.returnRecv(BigInteger.valueOf(i)));
                    json.put("Mon", userService.returnMon(BigInteger.valueOf(i)));
                    json.put("Time", userService.returnTime(BigInteger.valueOf(i)));
                    js.add(json);
                    System.out.println(json.toJSONString());
            }
        }
        else{
            for(int i=0;i<total;i++){
                String afford = userService.returnAfford(BigInteger.valueOf(i));
                String recv = userService.returnRecv(BigInteger.valueOf(i));
                if(afford.equals(username)||recv.equals(username)){
                    JSONObject json = new JSONObject();
                    json.put("Afford",afford);
                    json.put("Recv", recv);
                    json.put("Mon", userService.returnMon(BigInteger.valueOf(i)));
                    json.put("Time", userService.returnTime(BigInteger.valueOf(i)));
                    js.add(json);
                    System.out.println(json.toJSONString());
                }
            }
        }
        return js;
    }

    @RequestMapping("keySearchTran")
    @ResponseBody
    public JSONArray keySearchTran(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String name=request.getSession().getAttribute("user").toString();
        if(name==null)return null;
        BigInteger level=userService.returnLevel(name);
        int total=new Integer(String.valueOf(userService.getNumOfMsg()));
        JSONArray js = new JSONArray();
        if(level.compareTo(new BigInteger("1"))==0){
            for(int i=0;i<total;i++){
                String afford = userService.returnAfford(BigInteger.valueOf(i));
                String recv = userService.returnRecv(BigInteger.valueOf(i));
                if(afford.equals(username)||recv.equals(username)){
                    JSONObject json = new JSONObject();
                    json.put("Afford",afford);
                    json.put("Recv", recv);
                    json.put("Mon", userService.returnMon(BigInteger.valueOf(i)));
                    json.put("Time", userService.returnTime(BigInteger.valueOf(i)));
                    js.add(json);
                    System.out.println(json.toJSONString());
                }
            }
        }
        else{
            return null;
        }
        return js;
    }

    @RequestMapping("searchUser")
    @ResponseBody
    public JSONArray searchUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UserService userService = new UserService();
        String username=request.getSession().getAttribute("user").toString();
        if(username==null)return null;
        //BigInteger level=userService.returnLevel(username);
        int total=new Integer(String.valueOf(userService.getNumOfUser()));
        JSONArray js = new JSONArray();
        for(int i=0;i<total;i++){
            String name=userService.returnName(BigInteger.valueOf(i));
            if(!name.equals(username)){
                JSONObject json = new JSONObject();
                json.put("Username", userService.returnName(BigInteger.valueOf(i)));
                json.put("Money", userService.searchBalance_id(BigInteger.valueOf(i)));
                js.add(json);
                System.out.println(json.toJSONString());
            }
        }
        return js;
    }

    @RequestMapping("returnBalance")
    @ResponseBody
    public String returnBalance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserService userService = new UserService();
        String username=request.getSession().getAttribute("user").toString();
        if(username==null)return null;
        return String.valueOf(userService.searchBalance_name(username));
    }
}