# 🌐 Webアプリケーション開発ポートフォリオ（Java / JSP / Servlet）

## 📘 概要
Java / JSP / Servlet / SQL を用いて開発した Web アプリケーションです。  
ユーザー管理・データ登録・検索などの CRUD 処理を実装し、MVC 設計と DB 連携を学習目的で作成しました。

---

## 🛠 開発環境
| 項目 | 内容 |
|------|------|
| 言語 | Java（JDK 17 など） |
| フレームワーク | Servlet / JSP（Jakarta EE） |
| データベース | H2DB |
| ビルドツール | Apache Maven または Eclipse Dynamic Web Project |
| アプリケーションサーバー | Apache Tomcat 10.x |
| IDE | Eclipse / VS Code |
| バージョン管理 | Git / GitHub |
| OS | macOS / Windows |

---

## 🧩 機能一覧
| カテゴリ | 内容 |
|------------|------|
| ユーザー管理 | 新規登録・ログイン・ログアウト・パスワード変更 |
| データ登録 | フォーム入力によるデータ追加・バリデーション処理 |
| データ検索 | キーワード検索・絞り込み検索・一覧表示 |
| お気に入り管理/登録/削除 | 登録データの編集・削除機能 |
| セッション管理 | ログインユーザー情報の保持とアクセス制御 |
| エラーハンドリング | 例外処理／404ページ|

---

## 📂 ディレクトリ構成（例）
```
project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── dao/
│   │   │   │   ├── AllergenDAO.java
│   │   │   │   ├── FavoriteDAO.java
│   │   │   │   ├── ProductDAO.java
│   │   │   │   └── UserDAO.java
│   │   │   ├── model/
│   │   │   │   ├── Allergen.java
│   │   │   │   ├── Product.java
│   │   │   │   └── User.java
│   │   │   ├── servlet/
│   │   │   │   ├── FavoriteAddServlet.java
│   │   │   │   ├── FavoriteListServlet.java
│   │   │   │   ├── FavoriteRemoveServlet.java
│   │   │   │   ├── FavoriteToggleServlet.java
│   │   │   │   ├── LoginServlet.java
│   │   │   │   ├── RegisterFormServlet.java
│   │   │   │   ├── RegisterServlet.java
│   │   │   │   ├── SearchFormServlet.java
│   │   │   │   └── SearchResultServlet.java
│   │   │   └── util/
│   │   │       └── DBUtil.java
│   │   ├── webapp/
│   │   │   ├── css/
│   │   │   │   ├── FavoriteList.css
│   │   │   │   ├── login.css
│   │   │   │   ├── loginResult.css
│   │   │   │   ├── mainMenu.css
│   │   │   │   ├── register.css
│   │   │   │   ├── registerResult.css
│   │   │   │   ├── searchForm.css
│   │   │   │   └── searchResult.css
│   │   │   ├── images/
│   │   │   │   └── (画像ファイル)
│   │   │   ├── js/
│   │   │   │   ├── allergenFilter.js
│   │   │   │   └── favorite.js
│   │   │   ├── META-INF/
│   │   │   ├── WEB-INF/
│   │   │   │   ├── web.xml
│   │   │   │   └── jsp/
│   │   │   │       ├── favoriteList.jsp         （使用CSS: FavoriteList.css）
│   │   │   │       ├── loginResult.jsp          （使用CSS: loginResult.css）
│   │   │   │       ├── login.jsp                （使用CSS: login.css）
│   │   │   │       ├── register.jsp             （使用CSS: register.css）
│   │   │   │       ├── registerResult.jsp       （使用CSS: registerResult.css）
│   │   │   │       ├── searchForm.jsp           （使用CSS: searchForm.css）
│   │   │   │       ├── searchResult.jsp         （使用CSS: searchResult.css）
│   │   │   │       └── mainMenu.jsp             （使用CSS: mainMenu.css）
│   │   │   ├── index.jsp
│   │   │   └── mainMenu.jsp
├── drawio/
│   └── (仕様書や図)
├── build/
├── memo.md
└── README.md

```

---

## 🗄 データベース構成・テーブル定義
### 📘 テーブル一覧

USERS：ユーザ情報
| カラム名 | 型 | 説明 |
|-----------|----|------|
| id | INT | 主キー（AUTO_INCREMENT） |
| name | VARCHAR(50) | ユーザー名 |
| email | VARCHAR(100) | メールアドレス |
| password | VARCHAR(255) | ハッシュ化されたパスワード |
| created_at | DATETIME | 登録日時 |

PRODUCTS：商品情報
| カラム名       | 型       | NOT NULL | 説明               |
| ---------- | ------- | -------- | ---------------- |
| PRODUCT_ID | INT     | YES      | 主キー              |
| NAME       | VARCHAR | YES      | 商品名              |
| CATEGORY   | VARCHAR | NO       | カテゴリ名（例：食品・飲料など） |

ALLERGENS：アレルゲン
| カラム名        | 型       | NOT NULL | 説明                 |
| ----------- | ------- | -------- | ------------------ |
| ALLERGEN_ID | INT     | YES      | 主キー                |
| NAME        | VARCHAR | YES      | アレルゲン名（例：卵、乳、小麦など） |
| TYPE        | VARCHAR | NO       | 種類（例：特定原材料）        |

PRODUCT_ALLERGENS：商品とアレルゲンの中間テーブル
| カラム名        | 型       | NOT NULL | 説明               |
| ----------- | ------- | -------- | ---------------- |
| PRODUCT_ID  | INT     | YES      | PRODUCTS への外部キー  |
| ALLERGEN_ID | INT     | YES      | ALLERGENS への外部キー |
| CONTAINS    | BOOLEAN | YES      | 含む場合 true        |

FAVORITES：お気に入り（ユーザ × 商品）
| カラム名       | 型         | NOT NULL | 説明             |
| ---------- | --------- | -------- | -------------- |
| USER_ID    | INT       | YES      | USERS の外部キー    |
| PRODUCT_ID | INT       | YES      | PRODUCTS の外部キー |
| CREATED_AT | TIMESTAMP | YES      | 登録日時           |

### 📘 ER図
<img width="775" height="482" alt="スクリーンショット (12)" src="https://github.com/user-attachments/assets/d474c1b8-ec63-41bb-ae71-8693d8cd40f8" />

---

## 🧠 設計方針・工夫点
- MVC設計：Servlet（Controller）、model,DAO（Model）、JSP（View）を分離
- データベースへの接続処理は util.DBUtil クラスで一元管理しています。
- SQLインジェクション対策として **PreparedStatement** を使用しています。
- パスワードは **ハッシュ化（SHA-256 / bcrypt）** して保存
- ER図・画面遷移図を用いて処理を明確化

---

## 📊 UML / 設計資料
画面遷移図（全体）
> <img width="493" height="566" alt="スクリーンショット 2025-11-19 151501" src="https://github.com/user-attachments/assets/c5dc6904-3104-40ca-9582-61e4811364ab" />



<img width="456" height="583" alt="スクリーンショット 2025-11-19 151535" src="https://github.com/user-attachments/assets/1e3dbca7-3fd5-49a2-bcbd-192a0cea4e28" />
<img width="584" height="499" alt="スクリーンショット 2025-11-19 151555" src="https://github.com/user-attachments/assets/68c38e0b-e2e1-48cf-9dc0-bdb42c3705e2" />
> <img width="627" height="519" alt="スクリーンショット 2025-11-19 151512" src="https://github.com/user-attachments/assets/61baa0f2-dcf1-4839-8849-2a4a3689b2d4" />

---

## 💬 使用技術のポイント
- **Servlet & JSP**：HTTPリクエスト処理・セッション管理・リダイレクト制御  
- **DAOパターン**：DB操作の共通化・保守性向上  
- **SQL**：CRUD・JOIN・トランザクション  
- **HTML/CSS**：UI / フォーム入力補助  
- **Tomcat**：WARデプロイ・ローカルテスト環境構築  

---

## 🧭 今後の拡張予定
- Bootstrap / Vue.js の導入でUI改善  
- REST API 化（JSON対応）  
- Docker による環境構築自動化  
- ログイン履歴・アクセスログの分析機能追加  
- JUnit / Mockito による単体テストの充実  

---

## 📸 画面キャプチャ（例）
 ログイン画面-<img width="1134" height="763" alt="スクリーンショット 2025-11-21 160758" src="https://github.com/user-attachments/assets/7098dfa8-9ce5-44c1-abe4-37b513258b13" />
 ユーザー登録画面
<img width="765" height="563" alt="スクリーンショット 2025-11-21 160810" src="https://github.com/user-attachments/assets/8a932115-d66f-4007-a7db-cbd3f89a629b" /><br>
ホーム画面
<img width="1281" height="464" alt="スクリーンショット 2025-11-21 160828" src="https://github.com/user-attachments/assets/c39a605e-9145-42b6-a5c1-117e52e983bd" />


---

## 🧾 ライセンス・著作権
このプロジェクトは学習目的で作成したものであり、商用利用は想定していません。  
各種ライブラリ・ツールのライセンスはそれぞれの作者に帰属します。

---

## 👤 作成者
- **Githubアカウント**：MKcooder1002  
- **開発期間**：2025年10月〜11月

---

## ✅ 最終更新日
2025-11-11

---

> ✏️ **編集方法**：VS Code / Typora などの Markdown 対応エディタで開くと、見出しや画像をプレビューできます。
