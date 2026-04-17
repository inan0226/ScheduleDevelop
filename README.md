
## 📅 일정 CRUD API 명세서

### 1. 일정 생성 (Create)
새로운 일정을 등록합니다.

| 항목 | 내용 |
| :--- | :--- |
| **기능** | 일정 작성 |
| **Method** | `POST` |
| **URL** | `/api/schedules` |
| **Request Header**| `Content-Type: application/json` |

**Request Body**
```json
{
  "title": "스프링 과제하기",
  "contents": "API 명세서 작성하고 엔티티 설계하기",
  "authorName": "홍길동",
  "password": "password123!"
}
```

**Response Body (201 Created)**
```json
{
  "id": 1,
  "title": "스프링 과제하기",
  "contents": "API 명세서 작성하고 엔티티 설계하기",
  "authorName": "홍길동",
  "createdAt": "2026-04-17T12:00:00",
  "updatedAt": "2026-04-17T12:00:00"
}
```

---

### 2. 전체 일정 목록 조회 (Read)
등록된 모든 일정을 조회합니다. (비밀번호는 응답에서 제외)

| 항목 | 내용 |
| :--- | :--- |
| **기능** | 전체 일정 조회 |
| **Method** | `GET` |
| **URL** | `/api/schedules` |

**Request**
* 파라미터 없음 (필요시 `?updatedAt=YYYY-MM-DD&managerName=홍길동` 등 필터링 조건 추가 가능)

**Response Body (200 OK)**
```json
[
  {
    "id": 2,
    "title": "점심 약속",
    "contents": "강남역 1번 출구",
    "authorName": "김철수",
    "createdAt": "2026-04-17T11:00:00",
    "updatedAt": "2026-04-17T11:00:00"
  },
  {
    "id": 1,
    "title": "스프링 과제하기",
    "contents": "API 명세서 작성하고 엔티티 설계하기",
    "authorName": "홍길동",
    "createdAt": "2026-04-17T12:00:00",
    "updatedAt": "2026-04-17T12:00:00"
  }
]
```

---

### 3. 선택 일정 단건 조회 (Read)
특정 ID의 일정을 조회합니다. (비밀번호는 응답에서 제외)

| 항목 | 내용 |
| :--- | :--- |
| **기능** | 선택 일정 단건 조회 |
| **Method** | `GET` |
| **URL** | `/api/schedules/{id}` |

**Request**
* **Path Variable**: `id` (Long, 조회할 일정의 고유 식별자)

**Response Body (200 OK)**
```json
{
  "id": 1,
  "title": "스프링 과제하기",
  "contents": "API 명세서 작성하고 엔티티 설계하기",
  "authorName": "홍길동",
  "createdAt": "2026-04-17T12:00:00",
  "updatedAt": "2026-04-17T12:00:00"
}
```

---

### 4. 선택 일정 수정 (Update)
특정 ID의 일정을 수정합니다. (비밀번호가 일치해야 수정 가능)

| 항목 | 내용 |
| :--- | :--- |
| **기능** | 선택 일정 수정 |
| **Method** | `PUT` |
| **URL** | `/api/schedules/{id}` |

**Request**
* **Path Variable**: `id` (Long, 수정할 일정 ID)

**Request Body**
```json
{
  "title": "스프링 과제 완료하기",
  "contents": "API 개발 완료 및 테스트 진행",
  "authorName": "홍길동",
  "password": "password123!" 
}
```

**Response Body (200 OK)**
```json
{
  "id": 1,
  "title": "스프링 과제 완료하기",
  "contents": "API 개발 완료 및 테스트 진행",
  "authorName": "홍길동",
  "createdAt": "2026-04-17T12:00:00",
  "updatedAt": "2026-04-17T13:30:00" 
}
```

---

### 5. 선택 일정 삭제 (Delete)
특정 ID의 일정을 삭제합니다. (비밀번호가 일치해야 삭제 가능)

| 항목 | 내용 |
| :--- | :--- |
| **기능** | 선택 일정 삭제 |
| **Method** | `DELETE` |
| **URL** | `/api/schedules/{id}` |

**Request**
* **Path Variable**: `id` (Long, 삭제할 일정 ID)

**Request Body**
```json
{
  "password": "kk9240061"
}
```

**Response Body (200 OK 혹은 204 No Content)**
```json
{
  "message": "일정이 성공적으로 삭제되었습니다."
}
```

---
