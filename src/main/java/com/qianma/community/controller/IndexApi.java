package com.qianma.community.controller;

import com.qianma.community.Model.Question;
import com.qianma.community.Model.User;
import com.qianma.community.dto.QueUsrDTO;
import com.qianma.community.service.QuestionService;
import com.qianma.community.utils.RedisUtil;
import com.qianma.community.utils.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/3/22
 */
@Controller
public class IndexApi {

    @Autowired
    RedisUtil redisUtil;
    @Value("${github.client.address}")
    private String address;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model){
        User loginUser = SystemUtil.getLoginUser();
        if (loginUser != null){
            model.addAttribute("user",loginUser);
        }
        List<QueUsrDTO> questionList = questionService.selectForIndex();
        model.addAttribute("questionList",questionList);
        model.addAttribute("address",address);
        return "index";
    }
    @GetMapping("exit")
    public String exit(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = "";
        User user = null;
        if (Optional.ofNullable(cookies).isPresent()){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")){
                    String tokenCookies = cookie.getValue();
                    String clientId = tokenCookies.split("_")[0];
                    token = tokenCookies.split("_")[1];
                    user = (User) redisUtil.hget("user", clientId);
                    break;
                }
            }
        }
        assert user != null;
        if (user != null)  redisUtil.hdel("user",user.getClientId());
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
