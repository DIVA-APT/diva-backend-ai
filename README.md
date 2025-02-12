# 💌 DIVA (Delivery of Investment Value by AI)

## 📋 개요
이 프로젝트는 AI 추론 기능을 HTTP API로 제공하고, 이를 도메인 서버로 전달하는 역할을 수행하는 Spring Boot 기반 백엔드 애플리케이션입니다.
<br>
RestFul API를 통해 AI 모델의 예측 결과를 제공하며, 추론 데이터 처리 기능을 포함합니다.

---

## 🚀 기술 스택
### **Backend**
- **Spring Boot**
- **Spring MVC**, **Spring Data JPA**
- **MySQL**
- **Naver Cloud VPC**

### **DevOps**
- CI/CD (GitHub Actions)

---

## 📂 폴더 구조
```
📚 src
 ┣ 📂 main
 ┃ ┣ 📂 java/comapt/diva_ai
 ┃ ┃ ┣ 📂 domain
 ┃ ┃ ┃ ┗ 📂 (특정 도메인 디렉토리, inference, expert.. etc)
 ┃ ┃ ┃   ┣ 📂 controller
 ┃ ┃ ┃   ┣ 📂 service
 ┃ ┃ ┃   ┣ 📂 repository
 ┃ ┃ ┃   ┣ 📂 entity
 ┃ ┃ ┃   ┗ 📂 dto  
 ┃ ┃ ┗ 📂 global
 ┃ ┃   ┣ 📂 aspect
 ┃ ┃   ┣ 📂 config
 ┃ ┃   ┣ 📂 entity (BaseTime 등)
 ┃ ┃   ┣ 📂 enums
 ┃ ┃   ┗ 📂 exception
 ┃ ┃
 ┃ ┗ 📂 resources
 ┃   ┣ 📜 application.properties
 ┃   ┗ 📜 logback-spring.xml
```

[//]: # (## ⚙️ **설치 및 실행 방법**)

[//]: # (### **1️⃣ 프로젝트 클론**)

[//]: # (```sh)

[//]: # (git clone https://github.com/DIVA-APT/diva-backend-ai.git)

[//]: # (cd diva-backend-ai)

[//]: # (```)

[//]: # ()
[//]: # (### **2️⃣ 환경 변수 설정 &#40;`.env` 또는 `application.yml`&#41;**)

[//]: # (```yaml)

[//]: # (server:)

[//]: # (  port: 8080)

[//]: # ()
[//]: # (spring:)

[//]: # (  datasource:)

[//]: # (    url: jdbc:mysql://localhost:3306/mydb)

[//]: # (    username: root)

[//]: # (    password: password)

[//]: # (  jpa:)

[//]: # (    hibernate:)

[//]: # (      ddl-auto: update)

[//]: # (```)

[//]: # ()
[//]: # (### **3️⃣ 실행**)

[//]: # (```sh)

[//]: # (./gradlew bootRun   # Gradle 사용 시)

[//]: # (mvn spring-boot:run # Maven 사용 시)

[//]: # (```)

---

## 🔥 **주요 기능**
### ✅ AI 추론
- Python 스크립트를 실행하여 AI 추론 수행
- 추론 결과를 DB에 저장 및 PK 제공

---

## 🏠 **ERD**
<img width="1044" alt="image" src="https://github.com/user-attachments/assets/dc0cec64-a8b2-45aa-8de9-e7947d2c9157" />

---

