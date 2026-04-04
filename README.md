# backend-spring-common

`backend-spring-common` は、複数バックエンドアプリから利用する **共通ライブラリ** です。  
このリポジトリ単体では業務 API や起動アプリを提供せず、再利用可能な共通機能を提供します。

本コミットでは、その共通機能の一つとして **AWS Cognito 認証連携を組み込みやすくする最小部品** を追加しています。

## 追加した機能（Cognito 認証サポート）

パッケージ: `com.example.backend.common.auth.cognito`

- `CognitoAuthFeatureProperties`
  - `common.auth.cognito.registration-id` を受け取る設定クラス（デフォルト: `cognito`）
- `CognitoLoginPathResolver`
  - ログイン開始パス（例: `/oauth2/authorization/cognito`）を解決
- `AuthenticatedUserInfoMapper`
  - `Authentication` から表示・ログ用の基本情報 `Map` を生成
- `CognitoAuthFeatureConfiguration`
  - 上記を Bean として提供する構成クラス

## 想定する使い方（利用側アプリ）

このリポジトリは共通ライブラリなので、実際の `SecurityFilterChain` や Controller は利用側アプリで定義します。

### 1) 依存追加

```xml
<dependency>
  <groupId>com.example.backend</groupId>
  <artifactId>backend-spring-common</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### 2) 利用側アプリの設定例

```yaml
common:
  auth:
    cognito:
      registration-id: cognito

spring:
  security:
    oauth2:
      client:
        registration:
          cognito:
            client-id: ${COGNITO_CLIENT_ID}
            client-secret: ${COGNITO_CLIENT_SECRET}
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope: openid,profile,email
        provider:
          cognito:
            issuer-uri: ${COGNITO_ISSUER_URI}
```

### 3) 利用側アプリでの最小実装イメージ

- `SecurityFilterChain` で `oauth2Login()` を有効化
- `/me` 等の API は利用側で実装し、`AuthenticatedUserInfoMapper` を利用して整形
- 画面や API は業務アプリ側責務として実装

## ビルド

```bash
mvn clean package
```

## テスト

このライブラリ内では、共通部品の単体テストのみを実施しています。

```bash
mvn test
```
