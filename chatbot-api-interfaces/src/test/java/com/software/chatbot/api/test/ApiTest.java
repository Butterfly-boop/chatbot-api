package com.software.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxq_access_token=0D96595E-B3EB-76FE-8F72-AC09737999BF_BE4197D43C086A40; abtest_env=product; zsxqsessionid=2b60f9779e639616c0faa7ea90966af1; sensorsdata2015jssdkcross={\"distinct_id\":\"812584221521882\",\"first_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"props\":{},\"$device_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzExMjk3NzlmOS0wNTBjMTkwMGM1YzZkNDgtMjYwMjFiNTEtMjA3MzYwMC0xODczMTEyOTc3YTExYzMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI1ODQyMjE1MjE4ODIifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"812584221521882\"}}; UM_distinctid=1873112b85c0-0482b1fed3145a-26021b51-1fa400-1873112b85d9ac");
        get.addHeader("Content-Type", "application/json, text/plain, */*");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
    @org.junit.Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/814288528518452/comments");
        post.addHeader("cookie", "sajssdk_2015_cross_new_user=1; zsxq_access_token=0D96595E-B3EB-76FE-8F72-AC09737999BF_BE4197D43C086A40; zsxqsessionid=2b60f9779e639616c0faa7ea90966af1; sensorsdata2015jssdkcross={\"distinct_id\":\"812584221521882\",\"first_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"props\":{},\"$device_id\":\"1873111342c8-0e2658f3583e2b-26021b51-2073600-1873111342d763\",\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg3MzExMjk3NzlmOS0wNTBjMTkwMGM1YzZkNDgtMjYwMjFiNTEtMjA3MzYwMC0xODczMTEyOTc3YTExYzMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI4MTI1ODQyMjE1MjE4ODIifQ==\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"812584221521882\"}}; UM_distinctid=1873112b85c0-0482b1fed3145a-26021b51-1fa400-1873112b85d9ac; abtest_env=beta");
        post.addHeader("Content-Type", "application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
