package com.eightpotatoes.nexters.core.util

import com.eightpotatoes.nexters.core.model.MenuCategory

object MenuUtils {
    // 카테고리 분류 기준 정의
    private val snackKeywords = listOf("스낵", "과자", "빵", "도넛", "핫도그", "토스트", "샌드위치", "떡꼬치", "알감자","쥐포","쏘떡","통감자","가판대","소세지","꼬지어묵","꽈배기","삼진어묵","뷔페인박스","소미미","토스타","프레즐","간식", "아메리카노","핫바","로띠번","소떡","알뜰","송편","수제어묵", "소시지", "군밤","군고구마","반건조 오징어", "옥수수","사과","꼬치","스틱","타코야끼", "닭꼬치","오징어바", "문어바","카페라떼","카푸치노","라떼","마끼아또","커피","만쥬","콜드브루","빙수","회오리","해물바","크로플","닭꼬 치","모히또","밀크블랙","오징어야채바","오징어")
    private val koreanKeywords = listOf("찌개", "제육", "국밥", "비빔밥", "구이", "볶음", "국수", "한식", "불고기", "된장", "김치", "김밥", "떡볶이", "순대", "만두", "쌈", "반찬", "라면","분식","남도분식","해장국","추어탕","곰탕","우거지","육개장","소고기무국","황태","도시락","순두부","미역국","무국","청국장","갈비탕", "냉묵밥","영양밥","알밥","표고버섯","설렁탕","묵밥","닭개장","떡갈비","공기밥","시래기","동태탕","두루치기","버섯덮밥","자율식당","칼제비","삽겹","조림","민태","계란찜","갈비찜", "짜파게티","태양제면소","고탑덮밥","주먹밥","덮밥","콩나물밥","보국시","뱅뱅정식","매운탕","콩나물","돼지갈비","재첩국","시락국","어리굴젓","백반","복지리탕","장어탕","비빔면","깨비정식","갑쭈새","짜글이","짜(글이)","파개장","내장탕","도가니탕","밥상","소라찜","수제비","들깨탕","임자탕","영상휴게소정식","도토리묵","나물밥","소쿠리정식","평창의아침","곤드레","우볶이","닭갈비","닭강정","육회","물회", "쫄면", "계란후라이","아침밥상","맛있는김","영산휴게소정식","참치마요","불백","솥밥","뽕잎","건강한상","부대찌개","돼지찜","이로운죽","곤이탕","고등어","닭계장","놀부","삼겹","행담도휴게소정식","만둣국", "카페모카","황제탕","능이버섯","미나리","양반","뭇국","찜닭","쭈꾸미","감자탕","매송한상","비비고","누룽지","향단이정식","반상","해물탕","후레쉬빌","옹심이","수제화")
    private val westernKeywords = listOf("스파게티", "파스타", "스테이크", "버거", "피자", "샐러드", "양식", "돈까스", "팬케이크", "오믈렛", "스프","돈가스","함박","생선가스","돈가까스","함평휴게소정식","등심가스","치즈스틱","닭다리튀김","새우튀김","모둠가스", "가스모듬정식","모듬가스","왕치츠롤","몽룡이정식","향당이정식","함안휴게소","이서휴게소정식", "오수휴게소정식","모듬 까스","외동휴게소","공주휴게소 정식","공주","안심까스","냉채가츠","치폴레","미트마니아 세트", "메가바이트세트", "비스크치즈새우","NBB","필라프","리조또","치즈롤까스","모듬튀김")
    private val chineseKeywords = listOf("짜장면", "짬뽕", "탕수육", "중식", "볶음밥", "마파두부", "양장피", "깐풍","짜장정식","자장면","유슬","짜장덮밥","탕수강정","짬짜면","자장밥","짜장","미니탕풍기")
    private val asianKeywords = listOf("우동", "돈부리", "카레", "아시안","모밀","카츠","냉면","치킨마요","유부유동","가라야게","라멘", "히노아지","팟타이","포아이니","가츠동","초밥","냉메밀","가라아게","하이라이스","메밀소바","오무라이스", "소바","생선까스","돈우세트","떡우세트","탄탄맨", "메밀전병", "카츠쿠라 정식","탄탄면","나시고랭","타이프라이등","돈가츠","신머이","오므라이스","가츠쿠라","함바구","일본식","타이프라이드")

    // 이름 기반 카테고리 할당 함수
    fun getCategoryByName(name: String): MenuCategory {
        return when {
            snackKeywords.any { name.contains(it) } -> MenuCategory.SNACK
            koreanKeywords.any { name.contains(it) } -> MenuCategory.KOREAN
            westernKeywords.any { name.contains(it) } -> MenuCategory.WESTERN
            chineseKeywords.any { name.contains(it) } -> MenuCategory.CHINESE
            asianKeywords.any { name.contains(it) } -> MenuCategory.ASIAN
            else -> MenuCategory.UNKNOWN
        }
    }

    // 분식은 스낵일까 한식일까. 일단 한식으로 분류
    // 닭다리튀김, 새우튀김은 뭘까. 일단 양식으로 분류
    // 수제화가 있다.. 한식으로 분류
}