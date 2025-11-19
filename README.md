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
| IDE | Eclipse / IntelliJ IDEA / VS Code |
| バージョン管理 | Git / GitHub |
| OS | macOS / Windows |

---

## 🧩 機能一覧
| カテゴリ | 内容 |
|------------|------|
| ユーザー管理 | 新規登録・ログイン・ログアウト・パスワード変更 |
| データ登録 | フォーム入力によるデータ追加・バリデーション処理 |
| データ検索 | キーワード検索・絞り込み検索・一覧表示 |
| 更新／削除 | 登録データの編集・削除機能 |
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

PRODUCTS：商品情報

ALLERGENS：アレルゲンマスタ

PRODUCT_ALLERGENS：商品とアレルゲンの中間テーブル

FAVORITES：お気に入り（ユーザ × 商品）


### テーブル定義例：users
| カラム名 | 型 | 説明 |
|-----------|----|------|
| id | INT | 主キー（AUTO_INCREMENT） |
| name | VARCHAR(50) | ユーザー名 |
| email | VARCHAR(100) | メールアドレス |
| password | VARCHAR(255) | ハッシュ化されたパスワード |
| created_at | DATETIME | 登録日時 |

| カラム名       | 型       | NOT NULL | 説明               |
| ---------- | ------- | -------- | ---------------- |
| PRODUCT_ID | INT     | YES      | 主キー              |
| NAME       | VARCHAR | YES      | 商品名              |
| CATEGORY   | VARCHAR | NO       | カテゴリ名（例：食品・飲料など） |

| カラム名        | 型       | NOT NULL | 説明                 |
| ----------- | ------- | -------- | ------------------ |
| ALLERGEN_ID | INT     | YES      | 主キー                |
| NAME        | VARCHAR | YES      | アレルゲン名（例：卵、乳、小麦など） |
| TYPE        | VARCHAR | NO       | 種類（例：特定原材料）        |

| カラム名        | 型       | NOT NULL | 説明               |
| ----------- | ------- | -------- | ---------------- |
| PRODUCT_ID  | INT     | YES      | PRODUCTS への外部キー  |
| ALLERGEN_ID | INT     | YES      | ALLERGENS への外部キー |
| CONTAINS    | BOOLEAN | YES      | 含む場合 true        |

| カラム名       | 型         | NOT NULL | 説明             |
| ---------- | --------- | -------- | -------------- |
| USER_ID    | INT       | YES      | USERS の外部キー    |
| PRODUCT_ID | INT       | YES      | PRODUCTS の外部キー |
| CREATED_AT | TIMESTAMP | YES      | 登録日時           |

---

## 🧠 設計方針・工夫点
- MVC設計：Servlet（Controller）、model,DAO（Model）、JSP（View）を分離
- データベースへの接続処理は util.DBUtil クラスで一元管理しています。
- SQLインジェクション対策として **PreparedStatement** を使用しています。
- パスワードは **ハッシュ化（SHA-256 / bcrypt）** して保存
- ER図・シーケンス図を用いて処理を明確化

---

## 📊 UML / 設計資料
> 以下のファイルを差し替えてください（今はプレースホルダー画像です）：
>
> - `docs/usecase.png`：ユースケース図  
> - `docs/uml_sequence.png`：シーケンス図  
> - `docs/class_diagram.png`：クラス図  
>
> 例：  
> ![シーケンス図](./docs/uml_sequence.png)

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
> - `docs/login_screen.png`  
> - `docs/list_screen.png`  
>
> 例：  
> ![ログイン画面](./docs/login_screen.png)

---

## 🧾 ライセンス・著作権
このプロジェクトは学習目的で作成したものであり、商用利用は想定していません。  
各種ライブラリ・ツールのライセンスはそれぞれの作者に帰属します。

---

## 👤 作成者
- **氏名（またはGitHubアカウント）**：Your Name  
- **開発期間**：2025年10月〜11月  
- **連絡先**：your-email@example.com  
- **GitHub**：https://github.com/yourname/java-webapp-portfolio

---

## ✅ 最終更新日
2025-11-11

---

> ✏️ **編集方法**：VS Code / Typora などの Markdown 対応エディタで開くと、見出しや画像をプレビューできます。
