package com.sparta.myselectshop.utils;

import com.sparta.myselectshop.domain.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {

    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "8Fr7ih9omHfHYfK4p6d7");
        headers.add("X-Naver-Client-Secret", "ibTu_4BcWc");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query="+query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray items = jsonObject.getJSONArray("items");
        List<ItemDto> ret = new ArrayList<>();

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            ret.add(itemDto);
        }

        return ret;
    }
}
