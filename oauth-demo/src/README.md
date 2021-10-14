## auth0 demo 

> 本质上这个 spring app 为一个 Resource Server，响应持有 token 的 http 请求

1. 注册登录 auth0 后在控制台新增 api，对应 yml 配置文件中的 audience
2. 此应用 yml 配置文件设置 issuer，spring security 会识别并配置好 AS authorization server。
   此外，auth0 会新增一个 app 对接这个 api。
3. 应用启动后，先对 AS 发起 POST 请求取得 token，类型为 client_crendential，详情见 auth0 控制台的 api-test 页演示；
4. 将 token 放入 http header Authorization Bearer 里，即可正常访问 API，否则会响应 401