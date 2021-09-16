package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.domain.ItemDto;
import com.sparta.myselectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> search(@RequestParam String query) {
        String result = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(result);
    }
}
