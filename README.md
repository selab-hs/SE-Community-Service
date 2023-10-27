# SE-Community-Service
> SE 랩 커뮤니티 서비스를 제공하는 서비스입니다
> 누구나 간단하게 가입해서 커뮤니티 기능을 사용할 수 있습니다

## SE-Community-Service Web Application v1.0
- 개발 참여 인원: 황인준, 황하림
- 개발 기간: 2023-07-22 ~ 2023-09-11

### 프로젝트 구성도
![SE Community 구성도](https://github.com/selab-hs/SE-Community-Service/assets/50690859/4b3e92eb-bf08-48e5-ab0d-70f8dbd5ae6d)

## 주요 기능
📑 회원 기능
- 회원가입
- 로그인
- 회원정보변경

📑 게시판 기능
- 공지사항 작성
- 자유게시판 작성
- 게시판 조회
- 키워드 검색
- 댓글 작성

## 프로젝트 개발 전략
![git-flow](https://github.com/selab-hs/SE-Community-Service/assets/50690859/86e2eaa2-a478-48d6-a86d-e5557133f6f4)

- Github PR을 이용한 Git Flow 전략
- main(master): ec2 서버 release 브랜치
- staging: 이후 배포하기 위한 통합 브랜치 (develop)
- feature: 기능 구현 브랜치
- fix: 버그 픽스 브랜치
- docs: 문서화 구현 브랜치
- submodule: 브랜치가 아닌 민감한 yml 정보를 다루는 private한 하위 저장소
