package com.qianma.community.controller;

import com.alibaba.fastjson.JSON;
import com.qianma.community.Model.User;
import com.qianma.community.dto.AccessTokenDTO;
import com.qianma.community.dto.GitHubUser;
import com.qianma.community.provider.GithubProvider;
import com.qianma.community.utils.RedisUtil;
import com.sun.deploy.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO 请说明此类的作用
 *
 * @author wangkq
 * @date 2020/3/22
 */
@Controller
public class AuthorizeApi {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.redirect_url}")
    private String redirectUrl;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = githubProvider.getUser(accessToken);
        if (gitHubUser != null){
            Map<String, Object> userMap = new HashMap<>();
            User user = new User();
            user.setClientId(gitHubUser.getId());
            user.setDesc(gitHubUser.getBio());
            user.setName(gitHubUser.getName());
            user.setStatus(1);
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMap.put(user.getClientId(), user);
            redisUtil.hmset("user", userMap);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}
