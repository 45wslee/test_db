# SpringBoot 용 base-project
### egovFrame 4.2 주요 변경 사항
- spring 버전 업그레이드(version 4 -> version 5)
- spring-boot 지원
- lombok 지원
- spring 설정파일 변경(xml -> java)
- log 처리 변경 (log4j -> logback)
- 개발환경(이클립스)는 java 17 이상, 실행환경(소스코드) 는 java 8 이상 지원(https://www.egovframe.go.kr/home/sub.do?menuNo=9)
- 

# 프로젝트 초기 세팅

## 1. 프로젝트 이름 변경 시 확인해야 할 파일
- build.gradle(group 태그)
- settings.gradle(rootProject.name 태그)

## 2. 최상위 패키지명 변경 시 확인해야할 파일

- EgovConfigAppCommon (@ComponentScan : basePackages)
- EgovConfigWebDispatcherServlet(@ComponentScan : basePackages)
- EgovConfigAppTransaction (transactionAdvisor 의 pointcut)
- resources/egovframework/mapper 하위 xml 파일의 class

## Eclipse 사용시 Gradle 프로젝트 import 방법

- File > Import > Gradle > Existing Gradle Project 선택 후 Next > 프로젝트 경로 지정 후 Finish

# 자주 사용되는 utils 테스트코드 작성
자주 사용되는 기능을 utils 클래스로 작성.
<br/>사용방법을 src/test 하위에 테스트 클래스를 작성하여 정리.

- [DateUtils.java](http://192.168.0.205:1090/thkim/base-proj-boot/-/blob/main/src/main/java/xyz/rootlab/common/utils/DateUtils.java)

# 패키지 구조
### 기본 구조
```
└── member
    ├── web
    ├── service
    ├── vo
    └── dao
```

### JPA 도입을 위해 추가
```
└── member
    ├── web
    ├── service
    ├── vo
    ├── dao
    ├── entity
    └── repository
```

# 권장사항
- entity 클래스는 테이블과 1:1 로 매핑하여 생성하며 api의 return 객체로 활용 금지.
- entity 클래스는 무분별한 데이터의 변경을 막기 위해 setter 사용 금지. 값을 변경할 일이 있을 경우 클래스 내 메소드를 생성하여 변경.
- Date, Calendar 클래스를 LocalDate, LocalDateTime 클래스로 변경하여 사용([참고자료](https://d2.naver.com/helloworld/645609))
- lombok 활용

```java
// getter, setter 설정
@Getter @Setter
public class SearchVO {
    
}
```

```java
// Dependency Injection
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;
}
```

# 참조 문서
[파일 업로드/다운로드](http://106.248.251.25:1090/thkim/base-proj-boot/-/blob/main/doc/FILE.md)

# 모바일/태블릿/PC 기기 구분 사용 방법
1. DeviceUtils 이용
    /**
     * 로그인 화면
     */
    @RequestMapping(value = "/login/login")
    public String login(HttpServletRequest request, ModelMap model) throws Exception {
        ...

        [사용법]
        Device currentDevice = DeviceUtils.getCurrentDevice(request);
        if (currentDevice.isMobile()) {
            return "app/mobile/mobileLogin/mobileLogin";
        }

        return "app/portal/login/login";
    }

2. parameter 주입 이용
    /**
     * 로그인 화면
     */
    @RequestMapping(value = "/login/login")
    public String login(HttpServletRequest request, ModelMap model, Device device) throws Exception {
        ...

        [사용법]
        if (device.isMobile()) {
            System.out.println(">>>>> 모바일 디바이스");
        } else if (device.isTablet()) {
            System.out.println(">>>>> 태블릿 디바이스");
        } else if (device.isNormal()) {
            System.out.println(">>>>> PC 디바이스");
        }

        return "app/portal/login/login";
    }

# 메세지 공통 (다국어 지원 or 노출 메세지 공통 관리)

기존 전자정부프레임워크에 적용된 코드를 사용하였으나 이는 Java, JSP에서만 사용 가능하여 JS에서도 활용 가능하도록 코드 변경
- 기존 전자정부프레임워크 코드는 삭제 (23.02.19)

resources/egovframework/message/com/message-common_ko.properties, messae-common_en.properties 생성
message-common_ko.properties에 테스트 메세지 추가

[Test Message]
info.test.message = 테스트 메세지

1. Java에서 사용 시
```java
@Controller
[추가]@RequiredArgsConstructor
public class NoticeController {

    private final MemberService memberService;
    private final NoticeService noticeService;

    private final FileService fileService;

    [추가]private final LocaleMessageSource messageSource;

    /**
     * 등록 화면
     *
     * @return - view
     * @throws Exception
     */
    @GetMapping("/notice/new")
    public String createForm(Locale locale) throws Exception {
        [사용법]System.out.println(">>> Test MessageSource : " + messageSource.getMessage("info.test.message", null, locale));
        return "notice/createForm";
    }

```

2. JSP에서 사용 시
```html
<body>
[사용법]<h1><spring:message code='info.test.message'/></h1>
<form id="frm" method="post" enctype="multipart/form-data">
    <input type="text" name="title"/>
    <textarea name="desc"></textarea>
</form>
</body>
```

3. JS에서 사용 시
사용하고자 하는 jsp 파일 head에 추가 또는 공통으로 사용하기 위한 header.jsp head에 추가
```js
<head>
    ...
    <script src="${pageContext.request.contextPath}/js/message.js?time=<%=session.getLastAccessedTime()%>"></script>
</head>
```


js에서 아래와 같이 사용<br/>
```js
[사용법]console.log(getMessage("info.test.message"));
```

# 공통 응답코드 정의
아래 오류코드는 ResponseCode enum 클래스에 정의하여 휴먼에러를 최소화하기 위해 enum 클래스 사용 권장

|code|msg| 상세설명                     |
|----|---|--------------------------|
|1000|Success| 성공                       |
|1010|No Data| 결과없음(상세조회, 리스트 검색)       |
|1011|Exist Info| 중복된 데이터가 존재할 시           |
|1013|Response Timeout| 응답 시간 초과                 |
|1013|Target server is disabled| 요청 대상 서버가 비활성 상태일 경우     |
|1400|Invalid parameter| 파라미터 값이 잘못되거나 누락됨        |
|1400|Invalid parameter [Scale Chk]| 파라미터의 길이값이 잘못됨           |
|1400|Invalid parameter [Pattern]| 파라미터값의 패턴이 잘못됨           |
|1400|Invalid format parameter| 파라미터값의 Data Type 이 잘못됨   |
|1400|Cast Error [data confirm]| 파라미터 cast 에러             |
|1401|Not Authenticated| 인증 실패                    |
|1401|Not Authorized| 권한 없음                    |
|1420|No Session Data| 세션에 값이 없을 경우             |
|1500|Failed To Update| 수정 실패                    |
|1500|Failed To Delete| 삭제 실패                    |
|1500|Failed To Insert| 등록 실패                    |
|1500|Failed To Upsert| 등록 / 수정 실패               |
|1500|Failed To Process| 내부에서 요청 처리에 실패(DB 처리 연관) |
|1500|Unknown Error| 알 수 없는 오류(정립되지 않은)       |
|1500|Not Implemented| 미구현 요청 호출                |

[사용법1] - ResponseHandler 를 사용하여 직접 응답
```java
public Response method() {
    String validateParams = "mbrId:{" + Validator.REQUIRED + "};" + "mbrPswd:{" + Validator.REQUIRED + "};";
    String invalidParams = Validator.validate(memberDto, validateParams);
    if (!invalidParams.isEmpty()) {
        return ResponseHandler.invalidParam(invalidParams);
    }
   
    return ResponseHandler.success();
}
```

[사용법2] - 예외를 사용하여 공통 처리
```java
public Response method() {
    String validateParams = "mbrId:{" + Validator.REQUIRED + "};" + "mbrPswd:{" + Validator.REQUIRED + "};";
    String invalidParams = Validator.validate(memberDto, validateParams);
    if (!invalidParams.isEmpty()) {
        throw new InvalidParamException(invalidParams, ResponseCode.INVALID_PARAM);
    }
   
    return ResponseHandler.success();
}
```
공통처리를 위해 common/exception 패키지 하위로 예외 클래스 생성<br/>
- AuthException
- BizException
- InvalidParamException

각 예외를 던졌을 때 CommonExceptionHandler 클래스에서 공통 처리<br/>
생성자를 적절히 호출하여 응답 메시지 생성

