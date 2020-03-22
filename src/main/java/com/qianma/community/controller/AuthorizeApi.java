package com.qianma.community.controller;

import com.alibaba.fastjson.JSON;
import com.qianma.community.dto.AccessTokenDTO;
import com.qianma.community.dto.GitHubUser;
import com.qianma.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(redirectUrl);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = githubProvider.getUser(accessToken);
        System.out.println("user= "+user);
        return "index";
    }
}
