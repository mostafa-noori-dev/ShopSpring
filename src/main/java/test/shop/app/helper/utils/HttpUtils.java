package test.shop.app.helper.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class HttpUtils<T> {
    final Class<T> type;
    public HttpUtils(Class<T> type){
        this.type=type;
    }
    public Class<T> getType() {
        return type;
    }
    public T callPost(String address, Object data) {
        Gson gson = getGson();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String bodyData = gson.toJson(data);
        HttpEntity<String> httpEntity = new HttpEntity<>(bodyData, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(address, HttpMethod.POST, httpEntity, String.class);
        T responseData = gson.fromJson(responseEntity.getBody(), getType());
        return responseData;
    }
    public ResponseEntity<T> CallGet(String address) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(address, getType());
        return responseEntity;
    }

    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson;
    }

}
