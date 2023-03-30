package com.software.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.software.chatbot.api.domain.zsxq.IZsxqApi;
import com.software.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.software.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi zsxqApi;

    @Test
    public void test_zsxqApi() throws IOException{
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId,cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getTalk().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            zsxqApi.answer(groupId,"sajssdk_2015_cross_new_user=1; zsxq_access_token=0D96595E-B3EB-76FE-8F72-AC09737999BF_BE4197D43C086A40; zsxqsessionid=2b60f9779e639616c0faa7ea90966af1; sensorsdata2015jssdkcross={\"distinct_id\":\"812584221521882\",\"first_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"props\":{},\"$device_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzExMjk3NzlmOS0wNTBjMTkwMGM1YzZkNDgtMjYwMjFiNTEtMjA3MzYwMC0xODczMTEyOTc3YTExYzMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI1ODQyMjE1MjE4ODIifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"812584221521882\"}}; UM_distinctid=1873112b85c0-0482b1fed3145a-26021b51-1fa400-1873112b85d9ac; abtest_env=beta",topicId,text);
        }
    }
}
