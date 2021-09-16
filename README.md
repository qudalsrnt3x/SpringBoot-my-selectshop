### 나만의 셀렉샵 만들기

> 스파르타코딩클럽 강의를 들으며 정리한 내용입니다.

<br>

#### 프로젝트 설계

1. 키워드로 상품 검색하고 그 결과를 목록으로 보여주기<br>
`/api/search?query=검색어 GET List<ItemDto>`
2. 관심 상품 등록하기<br>
`/api/products POST Product`
3. 관심 상품 조회하기<br>
`/api/products GET List<Product>`
4. 관심 상품에 원하는 가격 등록하고, 그 가격보다 낮은 경우 표시하기<br>
`/api/products/{id} PUT Long`
5. 관심 상품 삭제하기<br>
`/api/products/{id} DELETE Long`


