### 授权码模式
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
     RandomValueAuthorizationCodeServices#consumeAuthorizationCode
     用户登录成功之后又重定向回: /oauth/authorize， 生成code并与用户登录信息绑定
     AuthorizationEndpoint
     AuthorizationServerEndpointsConfigurer#tokenGranter
     TokenGranter -> CompositeTokenGranter

    ```
4. AuthorizationServerEndpointsConfigurer
   ```text
   a. 实例化 AuthorizationEndpoint
   b. 实例化 TokenEndpoint
   c. 实例化 CheckTokenEndpoint
   ```
5. 
### 密码模式
1. 获取token：
   ```text
   https://oauth.b.com/token?
     grant_type=password&
     username=USERNAME&
     password=PASSWORD&
     client_id=CLIENT_ID
   ```
2. 待看博客：https://blog.csdn.net/zhang_ming_xuan/article/details/95176833