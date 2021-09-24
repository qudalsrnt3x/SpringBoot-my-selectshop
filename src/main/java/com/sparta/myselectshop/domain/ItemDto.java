package com.sparta.myselectshop.domain;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Setter
@Getter
public class ItemDto {

    private String title;
    private String link;
    private String image;
    private String mallName;
    private String category1;
    private String category2;
    private int lprice;

    public ItemDto(JSONObject jsonObject) {
        this.title = jsonObject.getString("title");
        this.image = jsonObject.getString("image");
        this.link = jsonObject.getString("link");
        this.mallName = jsonObject.getString("mallName");
        this.category1 = jsonObject.getString("category1");
        this.category2 = jsonObject.getString("category2");
        this.lprice = jsonObject.getInt("lprice");
    }
}
