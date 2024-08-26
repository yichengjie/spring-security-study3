1. 获取code：
    ```text
    http://localhost:9999/oauth/authorize
        ?response_type=code&
        client_id=client-for-server&
        redirect_uri=http://localhost:8080/login/oauth2/code/authorizationserver&
        scope=profile
    ```
2. 获取token：
    ```text
    http://localhost:9999/oauth/token?
        client_id=client-for-server&
        redirect_uri=http://localhost:8080/login/oauth2/code/authorizationserver&
        grant_type=authorization_code&
        code=UAa75U
    -----------------------------
    Authorization Basic Auth:
        username: client-for-server
        password: client-for-server
    ```
3. 流程：
    ```text
     AuthorizationCodeTokenGranter
     DefaultTokenService#createAccessToken(this.getOAuth2Authentication(client, tokenRequest))
    ```