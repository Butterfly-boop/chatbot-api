package com.software.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.software.chatbot.api.domain.ai.IOpenAI;
import com.software.chatbot.api.domain.zsxq.IZsxqApi;
import com.software.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.software.chatbot.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Configuration
public class ChatbotTsk {
    private Logger logger = LoggerFactory.getLogger(ChatbotTsk.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;
    /*@Scheduled(cron = "0/10 * * * * ?")
    public void run(){
        try {
            if (new Random().nextBoolean()) {
                logger.info("{} 随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 打烊时间不工作，AI 下班了！");
                return;
            }
            //1,检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId,cookie);
            logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("{} 本次检索未查询到待会答问题");
                return;
            }

             //2，AI回答
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(topic.getTalk().getText().trim());
             //3，问题回复
            boolean status = zsxqApi.answer(groupId, "zsxq_access_token=0D96595E-B3EB-76FE-8F72-AC09737999BF_BE4197D43C086A40; sensorsdata2015jssdkcross={\"distinct_id\":\"812584221521882\",\"first_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"props\":{},\"$device_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzExMjk3NzlmOS0wNTBjMTkwMGM1YzZkNDgtMjYwMjFiNTEtMjA3MzYwMC0xODczMTEyOTc3YTExYzMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI1ODQyMjE1MjE4ODIifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"812584221521882\"}}; UM_distinctid=1873112b85c0-0482b1fed3145a-26021b51-1fa400-1873112b85d9ac; zsxqsessionid=771aa407dc3485a01d2487993a2283f7; abtest_env=beta", topic.getTopic_id(), answer);
            logger.info(" 编号：{} 问题：{} 回答：{} 状态：{}",topic.getTopic_id(), topic.getTalk().getText(), answer, status);

        }catch (Exception e){
            logger.error("{} 自动回答问题异常",e);

        }
    }*/
}
