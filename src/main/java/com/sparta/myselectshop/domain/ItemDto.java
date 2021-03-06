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
    private int lprice;

    public ItemDto(JSONObject jsonObject) {
        this.title = jsonObject.getString("title");
        this.image = jsonObject.getString("image");
        this.link = jsonObject.getString("link");
        this.mallName = jsonObject.getString("mallName");
        this.lprice = jsonObject.getInt("lprice");
    }
}
