package com.example.nba_today.retrofit

import com.example.nba_today.models.Games1
import io.reactivex.Observable
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IMyAPI {

    interface Games {
        @Headers(
            value = ["Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                "Accept-Encoding: gzip, deflate, br",
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
                "Cache-Control: max-age=0",
                "Connection: keep-alive",
                "Content-Encoding: gzip",
                //"Content-Type: application/json",
                //"Cookie: check=true; AMCVS_248F210755B762187F000101%40AdobeOrg=1; _ga=GA1.2.1931195643.1550729137; ug=5c6e3faf0b49120a3f92ba00165c9cb1; AMCVS_7FF852E2556756057F000101%40AdobeOrg=1; __gads=ID=46c65332cd5baf8c:T=1550729137:S=ALNI_MYM91Y-PPOpXuKQWiUjm_dXjHDuzg; _fbp=fb.1.1550729142989.1433649622; _omappvp=VODzYRMb6j6HWHsKh0tb6NL4ChskTgLHxrAJjJGEwOBEFSGJPUrmHHLyt4vlinwxACWv6Ix4RG0WChxeLpbNEtEo09lpwTCf; omSeen-gmc7mlm8fei83nwi8ymf=1551427845434; om-gmc7mlm8fei83nwi8ymf=1551427847881; _gcl_au=1.1.789299351.1551427859; AAMC_nba_0=REGION%7C6; aam_uuid=77110093357889820203581321823130529866; _parsely_visitor={%22id%22:%22b8563a88-16f2-4422-a6b0-14cacc5f53e6%22%2C%22session_count%22:1%2C%22last_session_ts%22:1551427862885}; mp_nba_store_mixpanel=%7B%22distinct_id%22%3A%20%22169384eb332289-0a565128583056-632d1e58-100200-169384eb33323f%22%7D; AMCVS_248F210755B762187F000101%2540AdobeOrg%40AdobeOrg=1; AMCV_248F210755B762187F000101%2540AdobeOrg%40AdobeOrg=-1303530583%7CMCIDTS%7C17957%7CMCMID%7C92036513947981696466256348769561697020%7CMCOPTOUT-1551435076s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C3.3.0; s_sess=%20s_cc%3Dtrue%3B%20tp%3D1873%3B%20s_ppv%3Dnba%25253Ailp%25253Avideo%252C39%252C33%252C727%3B; s_cc=true; s_ppvl=nba%253Ateams%253Ahawks%2C52%2C47%2C927%2C1326%2C627%2C1366%2C768%2C1%2CP; s_ppv=nba%253Ateams%253Ahawks%2C35%2C35%2C627%2C771%2C627%2C1366%2C768%2C1%2CP; s_tps=856; s_pvs=1710; AMCV_248F210755B762187F000101%40AdobeOrg=1687686476%7CMCIDTS%7C17965%7CMCMID%7C77135730844670159953583869978166382197%7CMCAAMLH-1552710769%7C6%7CMCAAMB-1552710769%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1552113169s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C3.0.0; _gid=GA1.2.1943361428.1552105970; ugs=1; AMCV_7FF852E2556756057F000101%40AdobeOrg=1687686476%7CMCIDTS%7C17965%7CMCMID%7C77138594803391086763584101452941143050%7CMCAAMLH-1552710770%7C6%7CMCAAMB-1552710770%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1552113170s%7CNONE%7CMCAID%7CNONE%7CvVersion%7C3.0.0; s_sq=%5B%5BB%5D%5D; mbox=PC#b36a378bdd0c481389be100ff27cc488.22_42#1615356862|session#2db4f88ba50649be80334757752312db#1552113922; ak_bmsc=21BC24348D0E765D1110219B297D54C0B81CDAA7CC5A0000DF9D845CC5F2101D~pl6itile+N5LQiIsuRqNKV35kU0g/JMG4Dgs/Fko+wJaZ6ONt2rxuuOgBmpmqsNjFLddBUOt0YKi/IAf2ENnZjj5FsY3reTW5rP9DA1HEeFJJrF9RsQ7O1gqcL6Gi5p69mWugDRO+Hk+yr7SZ/CIjuVEeC1Pa0xgg4+BRJTeMqLa7GSGgDk/a3aY9y6BsQt+ZyjkpTnoQiyP5vYrqMgscZEq7/lVKTNB2/dcs9kp+Snw8=; bm_sv=3A4939F001F391297CF38608442A5DE8~ZypwdhRbTD3o42D+oBK/spaFKw60r1Oz2v/Oso3ItBPbAIA/r+xjw4Npde6nenCv7EmDfl50fGwIxUyNed3UESVlwubEov/24+lZ/kZSlESP6eDP20VISJoG0IH/kmE/3JRGCUJv1fxva2V0uc8UBA==",
                //"Host: stats.nba.com",
                //"Upgrade-Insecure-Requests: 1",
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79"]
        )

        @GET("stats/scoreboardv2?gamedate=03.04.2019&leagueid=00&dayoffset=0")
        fun getGames(): Observable<Games1>
    }
//            @Query("gamedate") gamedate: String,
//            @Query("leagueid") leagueid: String,
//            @Query("dayoffset") dayoffset: String
        //       ): Observable<Games1>
    //}

//    interface Games1 {
//        @GET("json?origin=53.7124163,91.416742&destination=53.402971,91.083748&sensor=false&mode=driving&key=AIzaSyBM31XFZQh0PlMpYHtg0WReOhBtRoSg_VQ")
//        fun getGames1(): Observable<Data>
//    }
}