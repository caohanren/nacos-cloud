package com.study.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ParseJwtTest {
    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJST0xFX0FETUlOIiwiUk9MRV9VU0VSIiwiUk9MRV9BUEkiXSwiaWQiOjMsImV4cCI6MTYwMDAxMDc0MywiYXV0aG9yaXRpZXMiOlsiYmFjazptZW51OnNldDJyb2xlIiwibWFpbDp1cGRhdGUiLCJiYWNrOnBlcm1pc3Npb246ZGVsZXRlIiwicm9sZTpwZXJtaXNzaW9uOmJ5cm9sZWlkIiwiYmFjazptZW51OnNhdmUiLCJiYWNrOm1lbnU6cXVlcnkiLCJpcDpibGFjazpxdWVyeSIsImNsaWVudDpxdWVyeSIsImlwOmJsYWNrOnNhdmUiLCJmaWxlOmRlbCIsImlwOmJsYWNrOmRlbGV0ZSIsIm1haWw6cXVlcnkiLCJiYWNrOnVzZXI6cXVlcnkiLCJiYWNrOnJvbGU6cGVybWlzc2lvbjpzZXQiLCJzbXM6cXVlcnkiLCJiYWNrOnJvbGU6cXVlcnkiLCJjbGllbnQ6c2F2ZSIsImJhY2s6cGVybWlzc2lvbjpxdWVyeSIsImJhY2s6dXNlcjpyb2xlOnNldCIsImJhY2s6cm9sZTpzYXZlIiwibG9nOnF1ZXJ5IiwiZmlsZTpxdWVyeSIsImNsaWVudDp1cGRhdGUiLCJiYWNrOm1lbnU6dXBkYXRlIiwiYmFjazpyb2xlOnVwZGF0ZSIsImJhY2s6cm9sZTpkZWxldGUiLCJiYWNrOnVzZXI6cGFzc3dvcmQiLCJST0xFX1NVUEVSX0FETUlOIiwiYmFjazptZW51OmRlbGV0ZSIsImJhY2s6dXNlcjp1cGRhdGUiLCJtZW51OmJ5cm9sZWlkIiwiY2xpZW50OmRlbCIsIm1haWw6c2F2ZSIsInVzZXI6cm9sZTpieXVpZCIsImJhY2s6cGVybWlzc2lvbjpzYXZlIiwiYmFjazpwZXJtaXNzaW9uOnVwZGF0ZSJdLCJqdGkiOiIyOGY5NWViMC1lMGE5LTQ1NzctYTgyYi04NzA2NTRmZDRkMWMiLCJjbGllbnRfaWQiOiJjMSIsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.nGb2-ySdUrDrDlZDg2mvOyDb0FDKsUm8KpyTkWLgDcNpWWatAOlmAnG5vcY8U5LCgQPBZ9T9g3aSxbWbyPssYTYhk6Ue0pj9qRTN64MZLxEobCbms4ItoZZ752fD_OO67NyoRlfke5zMi0dWIbJlEhszfKP3Hh1sJtzqEtNTPJhgzigdKucozER2XDjkrE_RJKMjCzFPVEldCRCY-qTao4XcuS01JuniZT6O--6_EM6Q-JGMEc7zRTq4QjGVqL535PFjkRGj_WLslbJjJZwgHbWaQJh6fDubbcO8lMom2oDJz6I-s9AyEVFq0pMxoUbkdU1Xeny2aadcbzP0Xpp9-A";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY----- MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApoaxOu2Wz7jTxauhlYkk +aRoqjG/6Sl4NDW+5v/8vdMVLsvD9VpPzQOlaiOYec7zAREzUxt1G2xFR5//X7+p ypCv31QsBG30cV68625XzpajyBfWBHLkBrjvt2ND02TyKcGiBUz39xii+B7vIxsl 1gCpLjHtu5mhPULs5S2Fk7onioFX9c9+r7pahXF2hfogL9VITezrXH419poGrRrc RwYAu2BkXHFouaFJ1aaRp5mn9XUltjyBTaKVSwSYToX61JDHKeyeaFuE9F7B7j4k AEMlG8QnYo4R5Cy5GUIbYRmMj5osjXCkt5H60tGiVyV1lQZYHAd5ppc7l5Ucra5L +wIDAQAB -----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
