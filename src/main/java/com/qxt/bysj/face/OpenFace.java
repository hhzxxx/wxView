package com.qxt.bysj.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.utils.BiliRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Face", method = RequestMethod.POST)
public class OpenFace {
    @Autowired
    private BiliRequest biliRequest;

    @Value("${app.secret}")
    private String secret;
    @Value("${app.id}")
    private String appid;

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    public void login(LoginDto dto, HttpServletRequest request){
        if(dto!=null){
            String code = dto.getCode();
            String obj =  biliRequest.getHTMLContentByHttpGetMethod(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code",null);
            JSONObject jsonObject = JSON.parseObject(obj);
            if(jsonObject.containsKey("session_key")){
                String session_key = jsonObject.getString("session_key");
                String openid = jsonObject.getString("openid");

            }

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
