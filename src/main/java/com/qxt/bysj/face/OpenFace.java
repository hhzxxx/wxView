package com.qxt.bysj.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.domain.Tag;
import com.qxt.bysj.domain.TagXuser;
import com.qxt.bysj.domain.User;
import com.qxt.bysj.domain.Video;
import com.qxt.bysj.service.TagService;
import com.qxt.bysj.service.TagXuserService;
import com.qxt.bysj.service.UserService;
import com.qxt.bysj.service.VideoService;
import com.qxt.bysj.utils.BiliRequest;
import com.qxt.bysj.utils.PageRequest;
import com.qxt.bysj.utils.PageResult;
import com.qxt.bysj.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/Face", method = RequestMethod.POST)
public class OpenFace {
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagXuserService tagXuserService;
    @Autowired
    private VideoService videoService;
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
                String isNew = "old";
                if(user!=null){
                    user.setUpdatetime(date);
                    userService.updateSelective(user);
                }else {
                    isNew = "new";
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
                map.put("isNew",isNew);
//                map.put("id",user.getId().toString());
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

    @ResponseBody
    @RequestMapping(value = "/firstTagChoose", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> firstTagChoose(HttpServletRequest request) {
        Result<Object> result = new Result<>();
        try{
            List<Tag> list = tagService.firstTagChoose();
            List<Tag> resList = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                if(i<15){
                    resList.add(list.get(i));
                }else {
                    break;
                }
            }
            result.setData(resList);
            result.setCode("1");
        }catch (Exception e){
            result.setMessage("后台出错！");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/firstTagSave", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> firstTagSave(FirstTagSaveDto dto,HttpServletRequest request) {
        Result<Object> result = new Result<>();
        List<Integer> tagIds = dto.getTagList();
        String openid = dto.getOpenid();
        User user = userService.selectByOpenid(openid);
        Integer userId = user.getId();
        if(tagIds.size()>0){
            for(int i=0;i<tagIds.size();i++){
                TagXuser obj = new TagXuser();
                obj.setTagid(tagIds.get(i));
                obj.setUserid(userId);
                tagXuserService.insert(obj);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/videoPage", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> videoPage(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = videoService.findPage(pageQuery);
        result.setData(page);
        return result;
    }

    /**
     * 必须userId
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findIndexPage", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> findIndexPage(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = videoService.findIndexPage(pageQuery);
        List<Video> list = (List<Video>) page.getContent();
        List<Integer> idList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(!idList.contains(list.get(i).getId())){
                idList.add(list.get(i).getId());
            }else {
                list.remove(i);
            }
        }
        result.setData(page);
        return result;
    }




   //以下都是Dto
    static class FirstTagSaveDto{
        private String openid;
        private List<Integer> tagList;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public List<Integer> getTagList() {
            return tagList;
        }

        public void setTagList(List<Integer> tagList) {
            this.tagList = tagList;
        }
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
