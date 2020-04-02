package com.qxt.bysj.face;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qxt.bysj.domain.*;
import com.qxt.bysj.service.*;
import com.qxt.bysj.threads.TestThreadPoolManager;
import com.qxt.bysj.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/Face", method = RequestMethod.POST)
public class OpenFace {
    @Autowired
    private UserService userService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagXuserService tagXuserService;
    @Autowired
    private VideoService videoService;
    @Autowired
    private BiliRequest biliRequest;
    @Autowired
    private httpPost httpPost;
    @Autowired
    TestThreadPoolManager testThreadPoolManager;
    @Autowired
    private ObjXuserService objXuserService;
    @Value("${app.secret}")
    private String secret;
    @Value("${app.id}")
    private String appid;

    /**
     * 登陆
     * @return
     */
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

    /**
     * 用户信息储存
     * @return
     */
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

    /**
     * 标签列表
     * @return
     */
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

    /**
     * 首次添加用户标签
     * @return
     */
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

    /**
     * 视频全部查询接口
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/videoPage", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> videoPage(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = videoService.findPage(pageQuery);
        result.setData(page);
        return result;
    }

    /**
     * 首页推荐视频
     * 必须openId
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findIndexPage", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> findIndexPage(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = videoService.findIndexPage(pageQuery);
/*        List<Video> list = (List<Video>) page.getContent();
        List<Integer> idList = new ArrayList<>();
        List<Video> res = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(!idList.contains(list.get(i).getId())){
                idList.add(list.get(i).getId());
                res.add(list.get(i));
            }
        }
        page.setContent(res);
        page.setPageSize(res.size());*/
        result.setData(page);
        return result;
    }

    /**
     * 点击视频
     * 必须openId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tapVideo", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> tapVideo(@RequestBody tapVideoDto dto) {
        Result<Object> result = new Result<>();
        String openId = dto.getOpenId();
        Integer objId = dto.getObjId();
        User user = userService.selectByOpenid(openId);

        //查询用户视频关联信息
        Map<String, Object> objXuserQuery = new HashMap<>();
        objXuserQuery.put("objId",objId);
        objXuserQuery.put("objType",1);
        objXuserQuery.put("userId",user.getId());
        List<ObjXuser> objXuserList = objXuserService.find(objXuserQuery);
        ObjXuser entity = null;
        if(objXuserList.size()<1){
            entity = new ObjXuser();
            entity.setObjid(objId);
            entity.setUserid(user.getId());
            entity.setStatus(0);
            entity.setObjtype(1);
            objXuserService.insert(entity);
        }else {
            entity = objXuserList.get(0);
            objXuserService.update(entity);
        }
        //执行视频点击流程
        String orderNo = System.currentTimeMillis() + UUID.randomUUID().toString();
        testThreadPoolManager.addOrders(orderNo,openId,objId);

        //获取视频地址
        Video video = videoService.selectById(objId);
//        String obj =  httpPost.post4video(video.getAvid(),null);
//        Document doc = Jsoup.parse(obj);
//        Elements elements = doc.select("span[id=basic-addon1]").select("a");
//        String url = elements.get(0).attr("href");
        String obj =  biliRequest.getHTMLContentByHttpGetMethod("https://www.xbeibeix.com/api/bilibiliapi.php?url=https://www.bilibili.com/&aid="+video.getAvid()+"&cid="+video.getCid(),null);
        JSONObject jsonObject = JSON.parseObject(obj);
        String url = jsonObject.getString("url");
        System.out.println(url);
        objXuserList = objXuserService.find(objXuserQuery);
        entity = objXuserList.get(0);
        result.setMessage(url);
        result.setData(entity);
        return result;
    }

    /**
     * 点赞 踩 收藏
     * objType:1:视频 2:文章 3:评论
     * 必须openId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/doUserAction", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> doUserAction(@RequestBody tapVideoDto dto) {
        Result<Object> result = new Result<>();
        String openId = dto.getOpenId();
        Integer objId = dto.getObjId();
        Integer objType = dto.getObjType();
        String action = dto.getAction();

        if(openId!=null && openId.length()>0) {
            objXuserService.doUserAction(openId,objId,action,objType);
        }else {
            result.setMessage("openid为null");
            result.setCode("999");
        }

        return result;
    }

    /**
     * 历史记录 收藏列表
     * 必须openId
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findOldAndCollection", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> findOldAndCollection(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = objXuserService.findPage(pageQuery);
        result.setData(page);
        return result;
    }


    //https://xbeibeix.com/api/bilibilivideo.php?url=www.bilibili.com/video/av95643079


    /**
     * 评论
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reply", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> reply(@RequestBody tapVideoDto dto) {
        Result<Object> result = new Result<>();
        String openId = dto.getOpenId();
        Integer objId = dto.getObjId();
        Integer objType = dto.getObjType();
        String content = dto.getContent();
        String action = "reply";
        User user = userService.selectByOpenid(openId);
        Reply reply = null;
        if(user!=null && objId!=null && objType!=null && content.length()>0){
            objXuserService.doUserAction(openId,objId,action,objType);
            reply = new Reply();
            reply.setUuid(UUID.randomUUID().toString());
            reply.setContent(content);
            reply.setObjid(objId);
            reply.setObjtype(objType.toString());
            reply.setUserid(user.getId());
            replyService.insert(reply);
            Map<String, Object> replyQuery = new HashMap<>();
            replyQuery.put("uuid",reply.getUuid());
            reply = replyService.find(replyQuery).get(0);
            if(objType==1){
                Video video = videoService.selectById(objId);
                video.setReply(video.getReply()+1);
                videoService.update(video);
            }
        }
        result.setData(reply);
        return result;
    }

    /**
     * 分页获取评论列表
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findReplyPage", produces = "application/json", method = RequestMethod.POST)
    public Result<Object> findReplyPage(@RequestBody PageRequest pageQuery) {
        Result<Object> result = new Result<>();
        PageResult page = replyService.findPage(pageQuery);
        result.setData(page);
        return result;
    }

    /**
     * 停止服务
     * @param id
     * @return
     */
    @GetMapping("/end/{id}")
    public String end(@PathVariable Long id) {

        testThreadPoolManager.shutdown();

        Queue q = testThreadPoolManager.getMsgQueue();
        System.out.println("关闭了线程服务，还有未处理的信息条数：" + q.size());
        return "Test ThreadPoolExecutor start";
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

    static class tapVideoDto{
        private Integer objId;
        private Integer objType;
        private String openId;
        private String action; //good点赞 bad踩 collection收藏
        private String content;//回复内容

        public Integer getObjId() {
            return objId;
        }

        public void setObjId(Integer objId) {
            this.objId = objId;
        }

        public Integer getObjType() {
            return objType;
        }

        public void setObjType(Integer objType) {
            this.objType = objType;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
