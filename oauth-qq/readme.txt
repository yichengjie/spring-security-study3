1. 获取授权码
 模板：  https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=[YOUR_APPID]&redirect_uri=[YOUR_REDIRECT_URI]&scope=[THE_SCOPE]
 真实：  https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=100550231&redirect_uri=http://www.pinzhi365.com/qqLogin/callback.do&scope=test
 返回值：code=2A81DD84BE75394DA62FC170ED30C2EB

2.通过授权码获取Access Token
  模板：   https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=[YOUR_APP_ID]&client_secret=[YOUR_APP_Key]&code=[The_AUTHORIZATION_CODE]&state=[The_CLIENT_STATE]&redirect_uri=[YOUR_REDIRECT_URI]
  真实：   https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=100550231&client_secret=69b6ab57b22f3c2fe6a6149274e3295e&code=2A81DD84BE75394DA62FC170ED30C2EB&state=test&redirect_uri=http://www.pinzhi365.com/qqLogin/callback.do
          https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=100550231&client_secret=69b6ab57b22f3c2fe6a6149274e3295e&code=5D10770705F91DB8DB99024F771E0914&state=test&redirect_uri=http://www.pinzhi365.com/qqLogin/callback.do
  返回值： access_token=8F0444C9F9E5E5048332013A20AB631F&expires_in=7776000&refresh_token=727BF0AE325EE0708898B744D0A4DC91


3. 使用Access Token来获取用户的OpenID
   模板：  https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN
   真实：  https://graph.qq.com/oauth2.0/me?access_token=8F0444C9F9E5E5048332013A20AB631F
   返回值：callback( {"client_id":"100550231","openid":"A1DC7A0D50010C510BE686794EE766DA"} );

4. 使用Access Token以及OpenID来访问和修改用户数据
   模板： https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
   真实： https://graph.qq.com/user/get_user_info?access_token=8F0444C9F9E5E5048332013A20AB631F&oauth_consumer_key=100550231&openid=A1DC7A0D50010C510BE686794EE766DA
