package com.software.chatbot.api.domain.ai.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;


import com.software.chatbot.api.domain.ai.IOpenAI;
import com.software.chatbot.api.domain.ai.model.aggregates.AIAnswer;

import com.software.chatbot.api.domain.ai.model.vo.Choices;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAI implements IOpenAI {
    private Logger logger = LoggerFactory.getLogger(OpenAI.class);
    @Value("${chatbot-api.openAiKey}")
    private String openAiKey;

    String chatEndpoint = "http://nginx.web-framework-1qoh.1045995386668294.us-west-1.fc.devsapp.net/v1/chat/completions";

    List<Map<String, String>> dataList = new ArrayList<>();

    @Override
    public String doChatGPT(String question) throws IOException {
        /*CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer "+openAiKey);

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \""+question+"\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }*/


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        dataList.add(new HashMap<String, String>() {{
            put("role", "user");
            put("content", question);
        }});
        paramMap.put("messages", dataList);
        JSONObject param = new JSONObject(paramMap);
        JSONObject message = null;
        System.out.println(question);
        String body = HttpRequest.post("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + openAiKey)
                .header("Content-Type", "application/json")
                .body(question)
                .execute()
                .body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
        message = result.getJSONObject("message");

        return message.getStr("content");
    }

}
