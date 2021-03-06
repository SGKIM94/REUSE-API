# REUSE [![Build Status](https://travis-ci.com/SGKIM94/REUSE-API.svg?branch=master)](https://travis-ci.com/SGKIM94/REUSE-API)
핸드폰을 중고 거래하기 위한 APP과 연동하기 위한 API 서버

## 개발 환경
 - Spring boot 
 - JPA(QueryDsl - mariaDB)
 - Junit5
 
## 서버 운용 환경
 - AWS EC2
 - RDS
 - S3
 - travis - CI
 
## 개발 목표
 - ATDD 기반으로 개발되어야 한다.
 - 객체 지향적 개발을 우선 시 한다.
 - Test Converage 80% 이상을 목표로 개발한다.
 - Java 8 의 Lamda & Stream 을 기반으로 개발한다.


 ```
 진행 상황
  - 사용자 기능 추가
  - 게시글 관련 기능 추가
  - 품목 관련 기능 추가
  - 품목(이미지) 기능 추가
  - 카테고리 기능 추가
  - 품목 즐겨 찾기 기능 추가
  - 채팅 기능 추가(WebSocket, STOMP) / 추가 개발 진행 중
  - 판매자에 대한 리뷰 기능 추가
  - 구매자에 대한 리뷰 기능 추가
  - 리뷰에 대한 사용자의 점수를 관리하기 위한 기능 추가
  
 ```
 
 
 ## REUSE DATABASES
 ![reuse-databases](https://user-images.githubusercontent.com/35962910/104464465-4b629600-55f6-11eb-9964-0ad0a3c3aa8f.png)


