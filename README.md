# 📋 API 명세서 (API Documentation)

---

## 📅 일정(Schedule) API 명세서

### 1. 일정 생성 (Create)

새로운 일정을 등록합니다. (세션 로그인 필요)

| **항목** | **내용** |
| --- | --- |
| **기능** | 일정 작성 |
| **Method** | `POST` |
| **URL** | `/schedules` |

**Request Header**

- `Content-Type: application/json`
- `Cookie: JSESSIONID=...` (로그인 세션 필요)

**Request Body**

```json
{
  "title": "스프링 과제하기",
  "content": "API 명세서 작성하고 엔티티 설계하기",
  "authorName": "홍길동",
  "password": "password123!"
}
```

**Response Body (201 Created)**

```json
{
  "id": 1,
  "title": "스프링 과제하기",
  "content": "API 명세서 작성하고 엔티티 설계하기",
  "createdAt": "2026-04-22T12:00:00",
  "modifiedAt": "2026-04-22T12:00:00"
}
```

---

### 2. 전체 일정 목록 조회 (Read)

등록된 모든 일정을 조회합니다. (비밀번호는 응답에서 제외)

| **항목** | **내용** |
| --- | --- |
| **기능** | 전체 일정 조회 |
| **Method** | `GET` |
| **URL** | `/schedules` |

**Request**

- 파라미터 없음

**Response Body (200 OK)**

```json
[
  {
    "id": 2,
    "title": "점심 약속",
    "content": "강남역 1번 출구",
    "createdAt": "2026-04-22T11:00:00",
    "modifiedAt": "2026-04-22T11:00:00"
  },
  {
    "id": 1,
    "title": "스프링 과제하기",
    "content": "API 명세서 작성하고 엔티티 설계하기",
    "createdAt": "2026-04-22T12:00:00",
    "modifiedAt": "2026-04-22T12:00:00"
  }
]
```

---

### 3. 선택 일정 단건 조회 (Read)

특정 ID의 일정을 조회합니다. (비밀번호는 응답에서 제외)

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 일정 단건 조회 |
| **Method** | `GET` |
| **URL** | `/schedules/{id}` |

**Request**

- **Path Variable**: `id` (Long, 조회할 일정의 고유 식별자)

**Response Body (200 OK)**

```json
{
  "id": 1,
  "title": "스프링 과제하기",
  "content": "API 명세서 작성하고 엔티티 설계하기",
  "createdAt": "2026-04-22T12:00:00",
  "modifiedAt": "2026-04-22T12:00:00"
}
```

---

### 4. 선택 일정 수정 (Update)

특정 ID의 일정을 수정합니다. (비밀번호가 일치해야 수정 가능, 세션 로그인 필요)

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 일정 수정 |
| **Method** | `PUT` |
| **URL** | `/schedules/{id}` |

**Request Header**

- `Content-Type: application/json`
- `Cookie: JSESSIONID=...` (로그인 세션 필요)

**Request**

- **Path Variable**: `id` (Long, 수정할 일정 ID)

**Request Body**

```json
{
  "title": "스프링 과제 완료하기",
  "content": "API 개발 완료 및 테스트 진행",
  "password": "password123!" 
}
```

**Response Body (200 OK)**

```json
{
  "id": 1,
  "title": "스프링 과제 완료하기",
  "content": "API 개발 완료 및 테스트 진행",
  "createdAt": "2026-04-22T12:00:00",
  "modifiedAt": "2026-04-22T13:30:00" 
}
```

---

### 5. 선택 일정 삭제 (Delete)

특정 ID의 일정을 삭제합니다. (비밀번호가 일치해야 삭제 가능, 세션 로그인 필요)

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 일정 삭제 |
| **Method** | `DELETE` |
| **URL** | `/schedules/{id}` |

**Request Header**

- `Cookie: JSESSIONID=...` (로그인 세션 필요)

**Request**

- **Path Variable**: `id` (Long, 삭제할 일정 ID)
- **Query Parameter**: `password` (String, 일정 비밀번호)
   - *예시 URL*: `/schedules/1?password=kk9240061`

**Response Body (204 No Content)**

- 데이터 없음 (성공적으로 삭제됨)

---

## 👤 유저(User) API 명세서

### 1. 유저 로그인 (Login)

이메일과 비밀번호로 로그인하여 세션을 생성합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 로그인 처리 및 세션 생성 |
| **Method** | `POST` |
| **URL** | `/login` |

**Request Header**

- `Content-Type: application/json`

**Request Body**

```json
{
  "email": "user@example.com",
  "password": "password123!"
}
```

**Response Body (200 OK)**

```json
{
  "id": 1,
  "email": "user@example.com"
}
```

---

### 2. 유저 생성 (Create)

새로운 유저(회원)를 등록합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 유저 생성 (회원가입) |
| **Method** | `POST` |
| **URL** | `/users` |

**Request Header**

- `Content-Type: application/json`

**Request Body**

```json
{
  "userName": "홍길동",
  "email": "hong@example.com",
  "password": "password123!"
}
```

**Response Body (201 Created)**

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createAt": "2026-04-22T12:00:00",
  "modifyAt": "2026-04-22T12:00:00"
}
```

---

### 3. 전체 유저 목록 조회 (Read)

등록된 모든 유저를 조회합니다. (비밀번호 제외)

| **항목** | **내용** |
| --- | --- |
| **기능** | 전체 유저 목록 조회 |
| **Method** | `GET` |
| **URL** | `/users` |

**Request**

- 파라미터 없음

**Response Body (200 OK)**

```json
[
  {
    "id": 1,
    "userName": "홍길동",
    "email": "hong@example.com",
    "createAt": "2026-04-22T12:00:00",
    "modifyAt": "2026-04-22T12:00:00"
  }
]
```

---

### 4. 선택 유저 단건 조회 (Read)

특정 ID의 유저 정보를 조회합니다.

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 유저 단건 조회 |
| **Method** | `GET` |
| **URL** | `/users/{id}` |

**Request**

- **Path Variable**: `id` (Long, 조회할 유저 고유 식별자)

**Response Body (200 OK)**

```json
{
  "id": 1,
  "userName": "홍길동",
  "email": "hong@example.com",
  "createAt": "2026-04-22T12:00:00",
  "modifyAt": "2026-04-22T12:00:00"
}
```

---

### 5. 선택 유저 수정 (Update)

특정 ID의 유저 정보를 수정합니다. (본인 세션일 경우만 가능)

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 유저 정보 수정 |
| **Method** | `PUT` |
| **URL** | `/users/{id}` |

**Request Header**

- `Content-Type: application/json`
- `Cookie: JSESSIONID=...` (로그인 세션 필요)

**Request**

- **Path Variable**: `id` (Long, 수정할 유저 ID)

**Request Body**

```json
{
  "userName": "홍길동_수정",
  "email": "hong_updated@example.com"
}
```

**Response Body (200 OK)**

```json
{
  "id": 1,
  "userName": "홍길동_수정",
  "email": "hong_updated@example.com",
  "createAt": "2026-04-22T12:00:00",
  "modifyAt": "2026-04-22T13:30:00"
}
```

---

### 6. 선택 유저 삭제 (Delete)

특정 ID의 유저를 삭제합니다. (본인 세션일 경우만 삭제 가능)

| **항목** | **내용** |
| --- | --- |
| **기능** | 선택 유저 삭제 (회원탈퇴) |
| **Method** | `DELETE` |
| **URL** | `/users/{id}` |

**Request Header**

- `Cookie: JSESSIONID=...` (로그인 세션 필요)

**Request**

- **Path Variable**: `id` (Long, 삭제할 유저 ID)

**Response Body (200 OK)**

- 데이터 없음 (성공적으로 삭제됨)

img width="956" height="537" alt="Image" src="https://github.com/user-attachments/assets/46e01236-c4ac-4bcd-9921-4a0850baed6c" />

