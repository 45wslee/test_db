package xyz.rootlab.common.utils.http;

import org.junit.jupiter.api.Test;
import xyz.rootlab.common.utils.JsonUtils;

import java.util.Map;

class HttpUtilsTest {
    @Test
    public void test1() {

//        String result = HttpUtils.post("http://192.168.100.65:9000/json", "{\"a\":\"aaa\",\"b\":\"BBB\"}");
        String result = HttpUtils.postForm("http://192.168.100.65:9000/form-url", "a=aaa&b=bbb");
        Map map = JsonUtils.fromJson(result, Map.class);
        System.out.println("result = " + JsonUtils.toJsonPretty(map));
    }
}