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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author：zzh
 * @date: 2023/5/10
 * @Copyright：
 */
@RestController
@RequestMapping("/Chatbot")
public class AndroidController {
    @Resource
    private IOpenAI openAI;

    @GetMapping("/getAnswer")
    public String findAudioInfoById(String question) throws IOException {
        String answer = openAI.doChatGPT(question);
        return answer;
    }
}

