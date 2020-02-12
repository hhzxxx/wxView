package com.qxt.bysj.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.utils.Result;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.utils.BiliRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/Face", method = RequestMethod.POST)
public class OpenFace {
    private UserService userService;

    @Autowired
    public OpenFace(UserService service){
        this.userService = service;
    }

    @Autowired
    private BiliRequest biliRequest;

    @Value("${app.secret}")
    private String secret;
    @Value("${app.id}")
    private String appid;

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> login(LoginDto dto, HttpServletRequest request){
        Result<Object> result = new Result<>();
        result.setMessage("error");
        if(dto!=null){
            String code = dto.getCode();
            String obj =  biliRequest.getHTMLContentByHttpGetMethod(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code",null);
            JSONObject jsonObject = JSON.parseObject(obj);
            if(jsonObject.containsKey("session_key")){
                Date date=new Date();
                String session_key = jsonObject.getString("session_key");
                String openid = jsonObject.getString("openid");
                User user = userService.selectByOpenid(openid);
                if(user!=null){
                    user.setUpdatetime(date);
                    userService.updateSelective(user);
                }else {
                    user = new User();
                    user.setUuid(UUID.randomUUID().toString());
                    user.setStatus(0);
                    user.setCreatetime(date);
                    user.setUpdatetime(date);
                    user.setOpenid(openid);
                    userService.insert(user);
                }
                result.setCode("1");
                result.setMessage("sucess");
                Map<String,String> map = new HashMap<>();
                map.put("session_key",session_key);
                map.put("openid",openid);
                result.setData(map);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/userInfo", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> userInfo(UserInfoDto dto, HttpServletRequest request) {
        Result<Object> result = new Result<>();
        result.setMessage("error");
        if(dto!=null){
            Date date=new Date();
            String openid = dto.getOpenid();
            User user = userService.selectByOpenid(openid);
            if(user!=null){
                user.setUpdatetime(date);
                user.setAvatarurl(dto.getAvatarUrl());
                user.setProvince(dto.getProvince());
                user.setNickname(dto.getNickName());
                user.setCity(dto.getCity());
                user.setCountry(dto.getCountry());
                userService.updateSelective(user);
                result.setCode("1");
                result.setMessage("sucess");
            }else {
                result.setMessage("不存在");
            }
        }
        return result;
    }



    static class UserInfoDto{
        private String nickName;
        private String province;
        private String country;
        private String city;
        private String avatarUrl;
        private String openid;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
    }

    static class LoginDto{
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
