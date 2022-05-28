package com.sparta.week04.utils;

import com.sparta.week04.dto.ItemDto;
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
        headers.add("X-Naver-Client-Id", "LmI7q9J8VMSwr1DT_gJi");
        headers.add("X-Naver-Client-Secret", "hQfl6f70md");
        String body = "";   //  응답받은 결과물들이 들어간다.

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); // httpStatus 200이라는 숫자 404 500 이런게 다 들어가는 듯  status에
        String response = responseEntity.getBody(); //response - 우리가 보는 것?
        //System.out.println("Response status: " + status);
        //System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result){
        JSONObject rjson = new JSONObject(result);      //검색 결과가 담긴다.
        JSONArray items = rjson.getJSONArray("items");

        List<ItemDto>itemDtoList = new ArrayList<>();

        for(int i = 0; i < items.length(); i++){
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
//            System.out.println(itemJson);
//            String title = itemJson.getString("title");
//            String image = itemJson.getString("image");
//            int lprice = itemJson.getInt("lprice");
//            String link = itemJson.getString("link");
        }
        return itemDtoList;
    }

}