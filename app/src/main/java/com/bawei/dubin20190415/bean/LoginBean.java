package com.bawei.dubin20190415.bean;

/**
 * @作者 杜彬
 * @创建日期 2019/3/20
 */
public class LoginBean {

//    {"result":
// {"headPic":"http://172.17.8.100/images/small/default/user.jpg",
//            "nickName":"FM_n5800",
//            "phone":"17704567890",
//            "sessionId":"15552971348923786",
//            "sex":1,
//            "userId":3786},
//        "message":"登录成功",
//            "status":"0000"}

    private String message;
    private String status;
    private Content result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Content getResult() {
        return result;
    }

    public void setResult(Content result) {
        this.result = result;
    }

    public static class Content{
        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private int sex;
        private int userId;

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }


}
