package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        //service -> mybatis -> dao

        ServerResponse<User> response = iUserService.login(username, password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.creatBySuccess();
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    /**
     * 校验用户名、邮箱是否重复
     * @param str
     * @param type
     * @return
     */
    @RequestMapping(value = "chenkValid.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type){
        return iUserService.checkValid(str,type);
    }

    /**
     * 获取当前用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.creatBySuccess(user);
        }
        return ServerResponse.creatByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    /**
     * 获取密码提示问题
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    /**
     * 检查问题答案
     * @param username
     * @param questin
     * @param answer
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String questin, String answer){
    return  iUserService.checkAnswer(username, questin , answer);
    }

    /**
     * 忘记密码之重置密码功能
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken){
        return iUserService.forgetRestPassword(username, passwordNew,forgetToken);
    }

    /**
     * 登录状态的重置密码
     * @param session
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> resetPassword (HttpSession session, String passwordOld, String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.creatByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld, passwordNew, user);
    }
}
