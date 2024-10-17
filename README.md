# Internship Matching System API Documentation

인턴십 매칭 시스템(Internship Matching System)을 위한 API는 학생, 회사, 인턴십, 지원 관련 CRUD 기능과 검색 및 필터링을 지원해야 합니다. 또한, 기술 스택을 다루는 API도 필요합니다. 각각의 엔터티에 따라 API를 분류하고, 필요한 기능을 구현할 수 있습니다.

## 1. 학생(Student) 관련 API

### 학생 등록
- **POST** `/students`  
- **설명**: 새로운 학생을 등록합니다.  
- **요청 데이터**: 학생 이름, 이메일, 전화번호, 전공, 이력서 경로 등

### 학생 정보 조회
- **GET** `/students/{studentId}`  
- **설명**: 특정 학생의 정보를 조회합니다.  
- **응답 데이터**: 학생 정보 및 기술 스택

### 학생 목록 조회 (검색 필터 포함)
- **GET** `/students`  
- **설명**: 모든 학생 목록을 조회하거나 이름, 전공, 기술 스택 등의 필터로 검색합니다.  
- **요청 파라미터**: 이름, 전공, 기술 스택 등

### 학생 정보 수정
- **PUT** `/students/{studentId}`  
- **설명**: 특정 학생의 정보를 수정합니다.  
- **요청 데이터**: 수정할 필드 정보

### 학생 삭제
- **DELETE** `/students/{studentId}`  
- **설명**: 특정 학생을 삭제합니다.

### 학생 기술 스택 추가
- **POST** `/students/{studentId}/techstacks`  
- **설명**: 특정 학생에게 기술 스택을 추가합니다.  
- **요청 데이터**: 기술 스택 ID

### 학생 기술 스택 삭제
- **DELETE** `/students/{studentId}/techstacks/{techStackId}`  
- **설명**: 특정 학생의 기술 스택을 삭제합니다.

---

## 2. 회사(Company) 관련 API

### 회사 등록
- **POST** `/companies`  
- **설명**: 새로운 회사를 등록합니다.  
- **요청 데이터**: 회사 이름, 산업 분야, 위치, 웹사이트 URL, 설명 등

### 회사 정보 조회
- **GET** `/companies/{companyId}`  
- **설명**: 특정 회사의 정보를 조회합니다.

### 회사 목록 조회 (검색 필터 포함)
- **GET** `/companies`  
- **설명**: 모든 회사 목록을 조회하거나 이름, 위치, 산업 분야 등의 필터로 검색합니다.  
- **요청 파라미터**: 이름, 위치, 산업 분야 등

### 회사 정보 수정
- **PUT** `/companies/{companyId}`  
- **설명**: 특정 회사의 정보를 수정합니다.  
- **요청 데이터**: 수정할 필드 정보

### 회사 삭제
- **DELETE** `/companies/{companyId}`  
- **설명**: 특정 회사를 삭제합니다.

---

## 3. 인턴십(Internship) 관련 API

### 인턴십 등록
- **POST** `/internships`  
- **설명**: 새로운 인턴십을 등록합니다.  
- **요청 데이터**: 인턴십 제목, 설명, 위치, 회사 ID, 필수 기술 스택, 급여, 시작일, 종료일 등

### 인턴십 정보 조회
- **GET** `/internships/{internshipId}`  
- **설명**: 특정 인턴십의 상세 정보를 조회합니다.

### 인턴십 목록 조회 (검색 및 필터 포함)
- **GET** `/internships`  
- **설명**: 모든 인턴십 목록을 조회하거나 회사, 위치, 기술 스택, 기간 등의 필터로 검색합니다.  
- **요청 파라미터**: 회사 ID, 위치, 기술 스택, 시작일, 종료일 등

### 인턴십 정보 수정
- **PUT** `/internships/{internshipId}`  
- **설명**: 특정 인턴십의 정보를 수정합니다.  
- **요청 데이터**: 수정할 필드 정보

### 인턴십 삭제
- **DELETE** `/internships/{internshipId}`  
- **설명**: 특정 인턴십을 삭제합니다.

### 인턴십 기술 스택 추가
- **POST** `/internships/{internshipId}/techstacks`  
- **설명**: 특정 인턴십에 기술 스택을 추가합니다.  
- **요청 데이터**: 기술 스택 ID

### 인턴십 기술 스택 삭제
- **DELETE** `/internships/{internshipId}/techstacks/{techStackId}`  
- **설명**: 특정 인턴십의 기술 스택을 삭제합니다.

---

## 4. 지원(Application) 관련 API

### 인턴십 지원
- **POST** `/applications`  
- **설명**: 학생이 특정 인턴십에 지원합니다.  
- **요청 데이터**: 학생 ID, 인턴십 ID, 제출한 이력서 파일 경로

### 지원 상태 업데이트
- **PUT** `/applications/{applicationId}`  
- **설명**: 지원 상태를 업데이트합니다. (지원 중, 합격, 불합격)  
- **요청 데이터**: 상태 정보

### 학생의 지원 내역 조회
- **GET** `/students/{studentId}/applications`  
- **설명**: 특정 학생의 모든 지원 내역을 조회합니다.

### 인턴십 지원자 목록 조회
- **GET** `/internships/{internshipId}/applications`  
- **설명**: 특정 인턴십에 지원한 모든 지원자 목록을 조회합니다.

### 지원 내역 삭제
- **DELETE** `/applications/{applicationId}`  
- **설명**: 특정 지원 내역을 삭제합니다.

---

## 5. 기술 스택(TechStack) 관련 API

### 기술 스택 등록
- **POST** `/techstacks`  
- **설명**: 새로운 기술 스택을 등록합니다.  
- **요청 데이터**: 기술 스택 이름

### 기술 스택 목록 조회
- **GET** `/techstacks`  
- **설명**: 모든 기술 스택 목록을 조회합니다.

### 기술 스택 정보 수정
- **PUT** `/techstacks/{techStackId}`  
- **설명**: 특정 기술 스택의 정보를 수정합니다.

### 기술 스택 삭제
- **DELETE** `/techstacks/{techStackId}`  
- **설명**: 특정 기술 스택을 삭제합니다.

---

## 추가 기능 API

### 인턴십 추천 API
- **GET** `/students/{studentId}/recommendations`  
- **설명**: 학생의 기술 스택에 맞는 인턴십을 추천합니다.  
- **요청 파라미터**: 학생 ID
