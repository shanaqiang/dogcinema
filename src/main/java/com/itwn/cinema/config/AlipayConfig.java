package com.itwn.cinema.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/17
 * @since 1.0.0
 */

@Data
@Component
public class AlipayConfig {
    // 沙箱appid
    public static String app_id = "2016101300675855";
    // 私钥 pkcs8格式的
    public static String merchant_private_key = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQD1XaHboSUSn3TOIZaawS6ViuJ2gHApHqICPx34JdvWvBA3XaEquq9+8pvoHm11gKK/FlMIn33zw7VfBr9dU/U/nZ0XuN6xBF72/IZg3v0W6dC5RuQZDej/SS44FCEehm5mdmdhTRMMpg2q17ux8jYjUR7VWUeO6yuVQQcS9MqDIkBUJAfJ3eEoiEVjrZr6BKpCQ+8uFdk2k+FIitP/ZaaZXTO5RJuzl8vmS8b0tuJSL86ENETqbWetB7r9KUlrRLwDIrKSrDwj1FdyeMCFecc0LE/A7o/W8EZ2FDriv5zUktxWf/dhzmMPN904B1g1drVFh0obe1papDV43mKItsXvAgMBAAECggEBAJccxVRoWFax0dQpp4FDvJbo5DQrQehxL0HG8YQkfNIxHKOcjGeudervK8m3A568TH73FshWwdFhFNCjbTPjQ0WcomU7vBq1IG9ocrIyYxi7g4q3+D7d7ENdJJPxAby0+kUsYtrwBu1hT0WKe9RJ4cG6cgzfYUzJ1Ruk4uBv1lEPkF1SSCG338sRZ0oz/Dk5S98Plyv/WGIvG4vEUyvmvvoGFT0WIB2INnvDCPlMCkJJe9/QYEX7JJRzq9ko2drafi5lz6VsDMWObaE1eEQfjsp6pMuQ3Jp+JegNwi3n6p2xZSSpOvl2+sDXKlW6rYDYfhAkqnwZFU7l519KDebUoFECgYEA/7KYio+5DLNJ7T7NIraDSDtVyDo8L/EzDQqfrXeaM6DQ/LyjTis08pZSZKMAZ35AzCIs/WkbXcuZBqJ5JN03p90eDzsj+XAS+Xf7oq2Zxyhun7roSHy1YW8XAecT/NqCGmKHC82wpttb6Pvm6IVUhc+tgiP1Dk/sDgH1JzLOWwUCgYEA9afoo9qClt2InSduYFtriDUOnLL8zzplZb+MpwrykI+B7ojEe+Y6qX1Gq5l3b+qhC4iNQoSCFimVke8c9nIxMxhBNahqPwNl6Xebyu91ORqJFjBU84Ujql6iIPJfHf561rFl5q18obrOCyn+PeoRHjeWTffu6mdtg0J8nRHFt2MCgYEAk2POXCimgXSji3bk5jqQiEVzmBxBb104kS7rhiFoKJRtgxH1jGyJc8BEtC/lXsK+9SxKBLTein1+sv1amW5O05s45YywmusIdPa9fTNWbh+XTMl/dbE1GHnhqD4cHmeSEUV0FNC3ICdX3n+9xZB9fTiOCnNRobNeHWDxgGvf2UUCgYEA8HnFYBk4uyz0XjtKbqMvue+OdVE18DqPJjKEIsWxvFfvbq2OAmfXRzGuq68Y34ME0cCD3/jNS5q7o1c6t/oDNaxpsXhDQZo2+nwvE1KkIxlZ11HK53SO/GmVNsFs4XTLlT2lrlJobf3rU7xw+PUYescZqQurYuWKq/K7ABt0vFcCgYEA5r4hPh8e8Hkeegt2EcGBGwaniocoMHcMkZoF/XKID1aYn77yXtFpVRefR4x4HUeX/oVYKnBTzimcbZ7ay6AuSjRwffyRZeyR9oH1GLirP34v12wOl+Zv5/UTdZt09imCaeCa3daLJZZpeHj74SA/9Mo95umxe5FJC8LkRrgMOIc=";
    // 请求网关  固定
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    //异步通知地址
    public static String notify_url = "http://haowl123.free.idcfengye.com/notify_url";
    //同步地址
	public static String return_url = "http://haowl123.free.idcfengye.com/return_url";
    // 编码
    public static String charset = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String alipay_public_key ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    // RSA2
    public static String sign_type = "RSA2";
}
