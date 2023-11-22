### gardener - spring

### 너만의 정원을 가꿔봐! - 작가의 정원(23.09.21 ~ 23.10.11)

작가를 꿈꾸는 아마추어들의 글을 관리해 주고 좋은 기회를 만들어주는 서비스 - KOSTA final project
<br>

| 조원   | 역할                                                                                             |
| ------ | ------------------------------------------------------------------------------------------------ |
| 이수완 | 글쓰기 수정 삭제 좋아요, 댓글 쓰기 수정 삭제, 작가 구독 및 확인, 구독한 작가 글보기, 상세글 보기 |
| 임지윤 | 로그인, 로그아웃, 아이디 찾기, 비밀번호 찾기, 회원가입                                           |
| 정겨운 | 마이페이지(회원정보 수정, 탈퇴, 프로필사진 변경), 작가 신청, 작가 신청 취소                      |
| 이문행 | 메인 페이지, 검색, 내가 쓴 글 확인                                                               |

### <i>목표 - 데이터베이스의 구성부터 프론트와 백을 가리지 않고 개발해보기</i>

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

<i>이수완</i>

⭐<b>게시글 작성</b>

<details>
<summary>게시글 작성 보기</summary>
<img src="https://github.com/dev-swansea/gardener-spring/assets/79567992/a7a0c610-a204-4dc9-bd1f-1c00473bb05b" height="500">
</details>

- 직접 에디터를 구현하는 것은 아직은 어렵다고 판단하여 toast ui editor를 이용하였습니다. <br>
- 메인 이미지를 등록하는 부분은 이미지 파일을 서버 PC에 저장 후 이미지의 주소를 반환해주면, 해당 주소로 이미지를 요청하게 되고 바이너리 코드를 반환해줍니다. <br>
- 이미지 저장 시 중복을 피하기 위해 UUID와 main / content로 이미지를 나눠 저장하고 있습니다. <br>
- 하나의 카테고리를 설정할 수 있습니다.

<br>
<br>

⭐<b>상세글 확인 및 좋아요, 구독, 댓글 작성</b>

<details>
<summary>상세글 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/3332afa7-c0ae-4892-be8b-999ce347e160"></details>

- 좋아요, 구독하기 버튼을 클릭하면 초록색 배경화면으로 변경되어 구독 중임을 알 수 있습니다. <br>
- 댓글을 작성할 수 있으며 페이징처리가 되어 5개씩 보이게 만들었습니다. 또한 작성 시 내가 쓴 글을 볼 수 있게 마지막 댓글 페이지로 이동합니다. <br>
- 댓글의 수정과 삭제가 가능합니다. <br>
    - 좋아요와 신고는 구현하지 못했습니다. <br>
    
작성글과 댓글에선 내가 작성한 경우 수정, 삭제가 가능해야 하므로 게시글에서의 필명(nickname)과 세션의 필명을 비교하여 판단하고 하여 수정, 삭제 버튼의 display를 변경하며 화면에 보여주고 있습니다. <br>

<br>
<br>

⭐<b>구독 작가 확인 및 작가 글 보기</b>

<details>
<summary>구독 작가 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/d10a070a-4899-461f-b439-038b007d5abb">
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/905b826d-31c2-4f8d-88cd-f3e7fec0ba6a">
</details>

- 내가 구독한 작가의 목록을 확인할 수 있음
- 작가 이미지를 선택하면 작가 페이지로 이동하여 공개된 글을 볼 수 있음

<br>
<br>

⭐<b>좋아요 글 확인</b>

<details>
<summary>좋아요 글 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/e6c505c4-c13c-4aad-a0f6-b62a839148a8">
</details>

- 좋아요 한 글을 볼 수 있습니다. <br>
- 좋아요 글을 클릭하면 상세글을 볼 수 있습니다. <br>

<br>
<br>

<i>좋아요와 구독은 비동기로 처리하여 페이지 전환없이 확인할 수 있습니다. 이때 둘 다 가져오는 것을 무한스크롤로 만들었는데, 문제는 글을 가져올 때 비공개 글을 가져오게 되는 상황에 발생하게 됩니다. 5개씩 가져오게 했을 때 5개가 비공개이고 6번째에서 공개글이 있다면 해당 글을 가져오지 못하게 됩니다.  <br>

이 문제는 마이바티스에서 동적으로 처리하면 해결될 것이라고 생각합니다. 현재는 단순하게 db에서 글을 공개/비공개 상관없이 가져온 다음 프론트에서 처리하고 있습니다. <br>

쿼리문을 더 공부해서 원하는 데이터만을 가져올 수 있게 만들어야겠습니다.</i> <br>

---

<i>임지윤</i>

⭐<b>회원가입</b>

<details>
<summary>회원가입 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/3a7b21e4-97ec-4aeb-b843-5a4e063a9763">
</details>

- 회원가입 버튼을 누르면 모달창이 뜸

<br>
<br>

⭐<b>로그인</b>

<details>
<summary>로그인 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/fbc05b54-582d-4789-9143-7ef971d4ff7f">
</details>

- 로그인 버튼을 누르면 모달창이 뜸

<br>
<br>

⭐<b>아이디, 비밀번호 찾기</b>

<details>
<summary>아이디, 비밀번호 찾기 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/92b7fa4c-456e-48a1-a06c-65c7e6e4ad83">
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/b4027bca-1387-41fd-817f-a8d612e211ba">
</details>

- 필명(nickname)과 이메일을 이용하여 아이디를 찾음
- 아이디와 이메일을 이용하여 비밀번호를 찾음

<br>
<br>

<i>시큐리티 난이도가 높아 적용하지 못한 아쉬움이 있다. 또한 프로필 이미지 등록도 구현되지 못했다. 그래도 처음 배운 사람이 이정도면 훌륭하게 해낸거 같다. 위의 기능들은 내가 구현해 보면 되겠다, 날 위해 남겨둔 기능이라 생각하자.</i>

---

<i>정겨운</i>

⭐<b>마이페이지(정보 수정, 탈퇴), 작가 취소</b>

<details>
<summary>마이페이지 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/b933cedb-8130-45e6-a5d4-119952240856">
</details>

- 회원정보 수정 버튼을 누르면 아이디와 가입일을 제외한 인풋창의 readonly 속성이 사라져 수정이 가능함
- 작가인 경우 작가 신청 취소를 누르면 작가 자격이 취소된다.

<br>
<br>

⭐<b>작가 신청</b>

<details>
<summary>작가 신청 보기</summary>
<img height=500 src="https://github.com/dev-swansea/gardener-spring/assets/79567992/3701b63d-1a58-4a3f-b53a-c03b1b59ece5">
</details>

- 폼을 작성하고 제출하기 버튼을 누르면 자동으로 신청됨

<br>
<br>

<i>"신청 후 어떠한 조건으로 받을 것인가" 와 관리자 기능을 구현하지 못해 무조건 작가가 될 수 있도록 하였다. 대기 상태와 작가 권한도 추가하고 싶으며 여기서도 프로필 이미지 변경을 구현하지 못하였기 때문에 이미지는 없다.
개발을 처음배운 두 사람 모두 이 부분을 어려워했다. 책에 좋은 예시가 있어서 내가 이해한 것으로 가르쳐 주기 보단 책의 예시가 훨씬 좋다고 판단하여 관여하지 않았는데, 이게 좀 후회가 된다.</i>

---

<i>이문행</i>

⭐<b>메인 화면</b>

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

<i>imgur 라는 외부 이미지 호스팅 서버에 이미지를 저장하고 사용하기 때문에 첫 로딩 시간이 길다. 프로젝트 초기에 이미지를 저장할 수 있는 아마존의 S3를, 과금에 쫄지 말고 시도해 보았다면 하는 아쉬움이 남는다.</i>
