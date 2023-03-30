package com.software.chatbot.api.domain.zsxq.model.req;

/**
 * @author 小傅哥，微信：fustack
 * @description 请求对象
 * @github https://github.com/fuzhengwei
 * @copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ReqData {

    private String text;
    private String[] image_ids = new String[]{};

    public String[] getMentioned_user_ids() {
        return mentioned_user_ids;
    }

    public void setMentioned_user_ids(String[] mentioned_user_ids) {
        this.mentioned_user_ids = mentioned_user_ids;
    }

    private String[] mentioned_user_ids = new String[]{};


    public ReqData(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String[] image_ids) {
        this.image_ids = image_ids;
    }

}
