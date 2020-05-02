package com.yicj.core.controller;

import com.yicj.core.exception.MyException;
import com.yicj.core.model.ReturnData;
import com.yicj.core.model.StatusCode;
import com.yicj.core.model.User;
import com.yicj.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
 
    @RequestMapping("/login")
    public ReturnData login(HttpServletRequest request, String username, String password) {
        User user = userService.findUserByAccountAndPassword(username, password);
        if (user != null) {
            //HttpSession session = request.getSession();
            //session.setAttribute("loginUserId", user.getUserId());
            //redisTemplate.opsForValue().set("loginUser:" + user.getUserId(), session.getId());
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getUserId());
            redisTemplate.opsForValue().set("loginUser:" + user.getUserId(), session.getId());

            return new ReturnData(StatusCode.REQUEST_SUCCESS, user, "登录成功！");
        } else {
            throw new MyException(StatusCode.ACCOUNT_OR_PASSWORD_ERROR, "账户名或密码错误！");
        }
    }
 
    @RequestMapping(value = "/getUserInfo")
    public ReturnData get(long userId) {
        User user = userService.findUserByUserId(userId);
        if (user != null) {
            return new ReturnData(StatusCode.REQUEST_SUCCESS, user, "查询成功！");
        } else {
            throw new MyException(StatusCode.USER_NOT_EXIST, "用户不存在！");
        }
    }
}
