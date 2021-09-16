## 나만의 셀렉샵 만들기

> 스파르타코딩클럽 강의를 들으며 정리한 내용입니다.

<br>

### 프로젝트 설계

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

<br>

### 회원 관리 기능 요구사항 및 설계

>'나만의 셀렉샵' 에 이용자가 많아지면서, 회원 가입, 
> 로그인 기능을 통해 회원을 관리하고자하는 요구가 생겼습니다.

|기능|사용자|관리자|
|------|---|---|
|회원가입|- 입력 항목: 아이디, 패스워드, 이메일<br>- 아이디 중복 불가|- 입력 항목: 아이디, 패스워드, 이메일, 관리자 식별암호<br>- 아이디 중복 불가|
|회원로그인|- 필수 항목: 아이디, 패스워드<br>- 인증 조건: 회원 가입 시 입력한 아이디와<br> 패스워드가 일치|동일|
|회원 로그아웃|조건: 로그아웃 버튼을 클릭|동일|
|회원 별 <br>상품 등록 및 조회|- 회원 별 등록/조회 상품 구분<br>- 다른 사용자가 등록한 상품들은 조회 불가|모든 사용자가 등록한 <br>상품들 조회 가능|


1. 회원가입<br>
`/user/signup GET 회원가입 HTML`<br>
`/user/signup POST `
2. 회원 로그인<br>
`/user/login GET 회원로그인 HTML`<br>
`/user/login POST username, password`
3. 회원 로그아웃<br>
`/user/logout GET`

### 상품 등록 및 조회 API 수정

기존 (회원별)
1. 관심 상품 등록하기<br>
`/api/products POST Product`
2. 관심 상품 조회하기<br>
`/api/products GET List<Product>`

추가 (관리자용)
1. 모든 회원의 상품 조회<br>
`/api/admin/products`
