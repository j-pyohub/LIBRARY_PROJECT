## 📘 Branch Strategy & Commit Guide

## 🚀 1. Branch Strategy

#### 우리 프로젝트는 main + feature/ 두 단계 브랜치 구조를 사용합니다.
### 🔵 main
- 최신 안정 코드
- 테스트 완료 후 기능만 merge
- 직접 커밋 금지

### 🌿 feature/*

- 기능 개발용 브랜치

- 기능 개발 완료 후 깃 관리자가 main으로 merge

```
feature/login 

feature/manager 

feature/store 

feature/item

feature/stock

feature/item_order

feature/sales

feature/menu

```

## 🏷️ 2. Commit Convention

커밋 메시지는 아래 형식을 사용합니다.

#### 형식
```
type: description
```
- 영어
- 명령형(add, fix, update…)

| 타입     | 코드     | 설명                          | 예시                              |
|----------|----------|-----------------------------|-----------------------------------|
| feat     | 기능 추가 | 새로운 기능, API, 화면 추가          | add login screen                  |
| fix      | 버그 수정 | 오류 해결, 잘못된 로직 수정            | fix sales detail                  |
| style    | UI/스타일 | CSS, 레이아웃, 포맷 변경 (기능 변화 없음) | style item table layout           |
| refactor | 리팩터링  | 코드 구조 개선, 공통화 (기능 변화 없음)    | refactor stock filtering logic    |
| docs     | 문서      | README, 문서, ERD 업데이트        | docs update API spec              |
| test     | 테스트    | 테스트 코드 추가/수정                | test item repository test         |
| delete   | 삭제      | 사용하지 않는 파일/코드 삭제            | delete deprecated dto             |
| revert   | 되돌리기   | 이전 커밋 취소                    | revert wrong calculation commit   |
| wip      | 작업중    | 미완성 기능 임시 커밋                | wip implement stock movement      |
| merge      | 작업중    | 브랜치 병합 커밋                | merge feature/item into main      |
