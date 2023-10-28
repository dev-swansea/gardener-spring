### gardener - spring

#### 너만의 정원을 가꿔봐! - 작가의 정원(23.09.21 ~ 23.10.13)

작가를 꿈꾸는 아마추어들의 글을 관리해 주고 좋은 기회를 만들어주는 서비스 - KOSTA final project
<br>
| 조원   | 역할   |
| ------ | ------ |
| 이수완 | 글쓰기 수정 삭제 좋아요, 댓글 쓰기 수정 삭제, 작가 구독 및 확인, 구독한 작가 글보기, 상세글 보기 |
|임지윤|로그인, 로그아웃, 아이디 찾기, 비밀번호 찾기, 회원가입|
|정겨운|마이페이지(회원정보 수정, 탈퇴, 프로필사진 변경), 작가 신청, 작가 신청 취소|
|이문행|메인 페이지, 검색, 내가 쓴 글 확인|

#### <i>목표 - 데이터베이스의 구성부터 프론트와 백을 가리지 않고 개발해보기</i>


<details>
  <summary><b>사용한 기술<b></summary>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=yellow">
<img src="https://img.shields.io/badge/jquery-7DF1E?style=for-the-badge&logo=jquery&logoColor=yellow">
<img src="https://img.shields.io/badge/JavaScript-0769AD?style=for-the-badge&logo=JavaScript&logoColor=yellow">
<img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=yellow">
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=yellow">
<img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Gitgub&logoColor=yellow">

이 외에 뱃지로 없는 슬픈 JSP, Mybatis
</details>

<details>
<summary>ERD</summary>
<img src="https://github.com/dev-swansea/gardener-spring/assets/79567992/7a7f67c7-bddd-469e-842e-e810bd2f586c">
</details>

---
<i>이문행</i>
<br>
<br>
⭐<b>메인 화면</b>
<br>
<details>
  <summary>메인 화면 보기</summary>
<img src="https://github.com/dev-swansea/gardener-spring/assets/79567992/fc747d0e-0350-4b3d-ae74-3f77053310e2"  height="500px"> 
</details>

- 부트스트랩을 이용하여 중앙 이미지의 slide를 구성 <br>
- 카테고리를 클릭하면 해당 카테고리로 쓴 글 검색으로 이동 <br>
- 카테고리 아래 좋아요 순으로 글이 보여짐(개인적으로, 공개된 최신글 순서대로 보여줄 것) <br>
- 좋아요 글이 가장 많은 작가의 글을 가장 아래 카드 섹션으로 보여줌 (개인적으로, 기간별 좋아요 글이 많은 순서대로 변경할 것)

<br>
<br>

⭐<b>검색 후 화면</b> 
<details>
  <summary>검색 후 화면 보기</summary>
<img height="500" alt="검색" src="https://github.com/dev-swansea/gardener-spring/assets/79567992/f514139f-ddcd-4d01-ab15-ee0d52697be2"> 
</details>

- 글 제목, 작가명, 내용으로 검색이 가능함

<br>
<br>

⭐<b>내가 쓴 글 확인</b>
<details>
  <summary>내가 쓴 글 확인 보기</summary>
<img height="500" alt="내가 쓴 글" src="https://github.com/dev-swansea/gardener-spring/assets/79567992/93206bce-c9dc-4696-b3d2-8aba7d99b063"> 
</details>

- 본인이 쓴 글을 확인할 수 있음

<br>
<br>

⭐<b>공개글 확인</b>
<details>
  <summary>공개글 확인 보기</summary>
<img height="500" alt="공개글 보기" src="https://github.com/dev-swansea/gardener-spring/assets/79567992/4b9fd983-b704-41e9-a84d-30e9e26ccc30">
</details>

- 공개처리된 글을 확인할 수 있음


---

<i>이수완</i>
<br>
<b>게시글 작성</b>
<br>
<img src="https://github.com/dev-swansea/gardener-spring/assets/79567992/a7a0c610-a204-4dc9-bd1f-1c00473bb05b" height="500"><br>
- toast ui editor를 이용하여 에디터 구현 (에디터 css 변경해 볼것)
- <details>
  <summary>이미지 첨부 시 다이어그램 (부정확할 가능성 높음)</summary>
  <img height="350" src="https://github.com/dev-swansea/gardener-spring/assets/79567992/33d9f0c1-86b9-4670-94d8-4d6dbbf47749"><br>
</details> 
- 한 개의 카테고리와 공개 / 비공개 설정을 해야함(추후에 카테고리 복수 설정 가능하게 만들 것)

<b>게시글 확인 및 좋아요, 구독, 댓글 작성</b>
<br>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/3332afa7-c0ae-4892-be8b-999ce347e160"><br>
- 게시글 상세보기에서 좋아요 버튼을 누를 수 있음
- 작가 구독 할 수 있음
- 댓글을 작성할 수 있음
  - 댓글에 페이징 처리가 되어있음 (대댓글로 변경해보고 싶다.)
작성글과 댓글에선 내가 작성한 경우 수정, 삭제가 가능해야 하므로 게시글에서의 필명(nickname)과 세션의 필명을 비교하여 판단하고 하여 수정, 삭제 버튼의 display를 변경하며 화면에 보여주고 있다.
<br>

<b>구독 작가 확인 및 글보기</b>
<br>


---
