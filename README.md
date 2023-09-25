# MoonYuJin
숙명여대 멋사 아기사자 문유진🦁

<br>

### 🦁 멋사 1학기 4주차 과제 🦁

| 실습 <br> 번호 | 캡쳐 | 
|:------:|:------|
|`17번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/9e9f9345-c113-4e58-af3a-de1548c71cfa">|
|`18번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/f63eafdb-ba7c-4d28-8ff0-1c46736fe37f">|
|`19번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/9e9f9345-c113-4e58-af3a-de1548c71cfa">|
|`20번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/77f50b94-47b2-40d5-a0da-8b8d54cfe807">|
|`21번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/9e9f9345-c113-4e58-af3a-de1548c71cfa">|
|`22번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/95f6e4c1-1105-41ba-8b30-5d5e0482d1a2">|
|`23번`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/2bf82d10-014c-4a72-a638-1bb50660766b">|
|`24번(인천)`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/da117967-2f8b-4da3-8cb9-6c9693df8419">|
|`24번(도쿄)`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/38e527c0-41cf-4e8e-9592-03b4bdf4baaa">|
|`24번(파리)`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/9afe02ce-2360-405b-b59b-c0c6465a33f6">|
|`24번(카이로)`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/f8e59fd7-8b9f-42f9-bb48-cc56551f6e09">|
|`24번(런던)`|<img src="https://github.com/Likelion-at-SMWU-11th/MoonYuJin/assets/74898231/effddd39-aac4-4570-8ae4-f46bf552b775">|

<br>

## 🦁 멋사 2학기 2주차 과제 🦁
### 스프링 JPA 구조에 대한 보고서 작성하기

- 각 계층의 기능과 역할
  - Client: 서비스를 요청한다.
  - Controller: 사용자의 요청이 진입하는 지점으로 요청에 따라 어떤 처리를 할지 결정해준다. 단, controller는 결정만 해주고 실질적인 처리는 Service에서 담당하며 사용자에게 view를 응답으로 보내준다.
  - Service: 비즈니스 로직을 수행하는 계층으로 Repository와 소통하며 데이터를 DB에 넣거나 가져오며 재사용이 가능하다.
  - Repository: DB와 소통하는 역할을 하며 Entity를 받아와 DB와 연결한다. 오롯이 DB와의 연결만을 위해 존재한다.
  - DB: 데이터가 저장되어 있는 곳이다.
- DTO를 사용하는 이유
  1. 객체를 표현하는 View Layer와 객체를 저장하는 DB Layer의 역할을 분리하기 위해서
  2. Entity 객체의 변경을 피하기 위해서(프로그래머의 의도와 다르게 데이터가 변질될 수 있으므로)
  3. ResponseDTO, RequestDto는 요청사항에 따라서 자주 변경되므로 View와 통신하는 DTO 클래스를 분리해서 관리
  4. Entity 클래스에 원하는 데이터를 표시하기 위한 필드나 로직이 추가되면 객체 설계를 망가뜨릴 수 있으므로 도메인 모델링을 지키기 위해서

- 실습에 작성한 코드 JPA 구조로 설명
  - JPA란 자바 진영에서 ORM 기술 표준으로 사용되는 인터페이스 모음으로 Hibernate, Spring JPA가 대표적이다. JPA는 객체와 테이블을 자동으로 매핑시키기 때문에 쿼리문 작성 없이도 DB에 접근이 가능하다.
  - JPA 어노테이션
    1. @Entity - DB 테이블을 매핑하며 JPA가 관리
    2. @Id - 해당 필드를 기본키로 지정
    3. @GeneratedValue - 기본키 생성 설정을 할 수 있으며 자동 생성 기능 보유
    4. @Column - 객체 필드를 테이블 컬럼에 매핑
  - Repository 생성 및 상속: DB 접근을 위한 Repository를 생성하여 JpaRepository 상속을 받아서 기본적인 CRUD 메소드 제공
