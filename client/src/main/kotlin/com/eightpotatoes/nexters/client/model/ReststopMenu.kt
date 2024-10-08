package com.eightpotatoes.nexters.client.model

data class ReststopMenu(
    val standardCode: String,
    val reststopName: String,
    val foodName: String,
    val description: String,
)

object ReststopRecommendMenuData {
    val menuList = listOf(
        ReststopMenu("000582", "문의청남대(영덕)휴게소", "파채제육덮밥", "매콤한 제육볶음과 아삭한 파채의 조화"),
        ReststopMenu("000342", "김천(부산)휴게소", "대구식궁중육개장(영양솥밥)", "얼큰한 육개장과 영양 솥밥의 궁합"),
        ReststopMenu("000144", "이천(남이)휴게소", "[미타우동]텐푸라우동", "바삭한 텐푸라가 올라간 일본식 정통 우동"),
        ReststopMenu("000261", "서천(서울)휴게소", "김치가츠우동(사골맛)", "진하게 끓인 사골 육수와 볶음 김치가 더해진"),
        ReststopMenu("000026", "죽암(부산)휴게소", "보은대추왕갈비탕", "갈비 육즙과 보은 대추로 우려 정성스레 끓인"),
        ReststopMenu("000166", "산청(통영)휴게소", "흑돼지소라찜", "지리산 흑돼지, 쫄깃한 소라, 특제 양념이 들어간"),
        ReststopMenu("000165", "산청(대전)휴게소", "흑돼지소라찜", "지리산 흑돼지, 쫄깃한 소라, 특제 양념이 들어간"),
        ReststopMenu("000289", "고성공룡나라(대전)휴게소", "흑돼지소라찜", "명품먹거리 산청 돌담의 대표메뉴"),
        ReststopMenu("000150", "오창(남이)휴게소", "능이버섯설렁탕", "능이버섯이 들어가 시원하고 든든한"),
        ReststopMenu("000554", "매송(서울)휴게소", "(한촌)설렁탕", "오래 끓인 진한 국물의 든든한 한 끼"),
        ReststopMenu("000015", "천안삼거리(서울)휴게소", "해주비빔밥", "신선한 재료와 닭고기로 만든 황해토 향토 음식"),
        ReststopMenu("000141", "이천쌀(하남)휴게소", "영양돌솥비빔밥", "21년도 휴게소 대표 음식으로 선정된"),
        ReststopMenu("000358", "이서(천안)휴게소", "명품꼬막비빔밥", "신선한 꼬막과 각종 나물로 정성스럽게 만든"),
        ReststopMenu("000050", "평사(부산)휴게소", "경산대추비빔밥정식", "지역 농산물 경산 대추를 활용한"),
        ReststopMenu("000355", "이서(순천)휴게소", "콩불고기보리비빔밥", "콩불고기와 채소 강된장을 활용한 식물성 메뉴"),
        ReststopMenu("000401", "보성녹차(영암)휴게소", "보성꼬막비빔밥", "보성산 꼬막과 함께 즐기는 고소한 맛"),
        ReststopMenu("000401", "보성녹차(영암)휴게소", "꼬막비빔밥콤보세트", "꼬막비빔밥과 다양한 반찬이 어우러진 세트"),
        ReststopMenu("000071", "문산(순천)휴게소", "엄마손된장찌개비빔밥", "깔끔하고 된장찌개와 나물, 강된장이 어우러진"),
        ReststopMenu("000249", "동명(부산)휴게소", "팔공비빔밥정식", "팔공산의 신선한 재료로 만든 건강식 비빔밥"),
        ReststopMenu("000103", "대천(목포)휴게소", "돌솥비빔밥", "고소한 돌솥에 담아낸 전통 한식 비빔밥"),
        ReststopMenu("000418", "강천산(대구)휴게소", "순창고추장돌솥비빔밥", "제철나물, 순창고추장을 넣어 고급스럽게 구성한"),
        ReststopMenu("000418", "강천산(대구)휴게소", "순창토마토고추장비빔밥", "건강 기능성 토마토 발효 고추장으로 만든"),
        ReststopMenu("000118", "여산(천안)휴게소", "서동마여산양파제육불고기", "양파의 단맛과 제육불고기의 매콤함이 어우러진"),
        ReststopMenu("000575", "장흥정남진휴게소", "장흥 표고 뚝배기 불고기", "장흥 표고버섯과 불고기를 뚝배기에 끓여낸"),
        ReststopMenu("000166", "산청(통영)휴게소", "한방우불고기", "한방 재료와 소불고기로 만들어 영양가 높은"),
        ReststopMenu("000165", "산청(대전)휴게소", "한방 우(牛)불고기(19년 ex-food 선정)", "식감좋은 우육과 여러가지 한방재료를 이용한"),
        ReststopMenu("000013", "망향(부산)휴게소", "EX라면", "풍성한 건더기에 계란까지 탁!"),
        ReststopMenu("000223", "단양팔경(부산)휴게소", "단양마늘떡갈비", "단양 대표메뉴떡갈비와 구수한 된장찌개"),
        ReststopMenu("000496", "의성(영덕)휴게소", "의성마늘돈가스", "의성 통마늘 장아찌가 올라간"),
        ReststopMenu("000032", "옥천(서울)휴게소", "옥천포도돈까스", "옥천 특산 포도로 만든 새콤달콤한 소스의"),
        ReststopMenu("000221", "단양팔경(춘천)휴게소", "마늘 왕 돈가스", "엄선한 돼지고기로 만든 푸짐한 양의"),
        ReststopMenu("000042", "추풍령(서울)휴게소", "통등심돈가스", "주문 즉시 튀기는, 감칠맛나는 정통 소스의"),
        ReststopMenu("000050", "평사(부산)휴게소", "대구따로국밥정식", "대구 10미중 하나인 얼큰한 국물의"),
        ReststopMenu("000046", "칠곡(서울)휴게소", "대구따로국밥", "국물이 일품인 칠곡 휴게소 최고 인기 메뉴"),
        ReststopMenu("000331", "안성맞춤(평택)휴게소", "그때그집돼지수육국밥", "안성시장의 60년 이상된 노포 맛집 입점"),
        ReststopMenu("000009", "안성(부산)휴게소", "한우소머리국밥", "24시간 끓인 풍부한 사골과 특제소스가 어우러진"),
        ReststopMenu("000554", "매송(서울)휴게소", "양갑조 한우국밥", "양갑조 할머니의 전통있는 한우국밥"),
        ReststopMenu("000013", "망향(부산)휴게소", "한우국밥", "한우 육수에 각종 야채와 양념을 넣어 푹 끓인"),
        ReststopMenu("000303", "괴산(창원)휴게소", "서울식당 괴산올갱이국밥", "유명한 괴산의 올갱이국 맛집 서울식당 입점"),
        ReststopMenu("000127", "백양사(순천)휴게소", "고우고한우사골곰탕", "생성정보통에 방영된 광주 본점의 맛집 제품"),
        ReststopMenu("000064", "진영(순천)휴게소", "장군차뚝배기갈비찜", "전통차인 장군차 잎과 갈비를 뚝배기에 담은"),
        ReststopMenu("000204", "여주(인천)휴게소", "매운돼지갈비찜", "19년도 휴게소 대표 음식으로 선정된"),
        ReststopMenu("000213", "홍천강(춘천)휴게소", "얼큰황태순두부_주물솥밥", "얼큰한 황태와 부드러운 순두부의 만남"),
        ReststopMenu("000217", "치악(부산)휴게소", "원주뽕잎곤드레 영양\"기(氣)밥", "15가지 치악산 잡곡, 뽕잎, 곤드레로 만든"),
        ReststopMenu("000217", "치악(부산)휴게소", "산나물만두감자옹심이", "강원도산 곤드레나물, 원주토토미로 만든"),
        ReststopMenu("000042", "추풍령(서울)휴게소", "지례흑돼지석쇠구이", "임금님 진상용이던 김천 지례면 흑돼지로 만든"),
        ReststopMenu("000003", "죽전(서울)휴게소", "용인특산품영양밥정식", "용인산 백옥쌀, 순지오이, 느타리버섯으로 만든"),
        ReststopMenu("000118", "여산(천안)휴게소", "닭갈비덮밥", "매콤한 닭갈비와 밥이 어우러진 든든한 한 끼"),
        ReststopMenu("000074", "사천(순천)휴게소", "실속건강버섯두부덮밥", "적당히 매콤한 소스와 건강한 두부의 콜라보"),
        ReststopMenu("000201", "문막(강릉)휴게소", "치악산복숭아 돼지불백 정식", "매콤한 돼지고기에 치악산 복숭아 효소를 넣은"),
        ReststopMenu("000273", "문경(창원)휴게소", "문경산나물밥", "문경지역산 건나물만 사용한 건강한"),

        ReststopMenu("000462", "탄천(순천)휴게소", "청주본가 왕갈비탕", "깊은 맛의 갈비와 청주의 전통을 담은"),
        ReststopMenu("000462", "탄천(순천)휴게소", "생생등심돈까스", "두툼한 등심으로 만든 바삭하고 고소한 돈까스"),
        ReststopMenu("000477", "청도새마을(부산)휴게소", "한우국밥", "청도에서 자란 한우를 듬뿍 넣어 깊은 맛이 나는"),
        ReststopMenu("000478", "청도새마을(대구)휴게소", "한우국밥", "청도에서 자란 한우를 듬뿍 넣어 깊은 맛이 나는"),
        ReststopMenu("000481", "장안(부산)휴게소", "기장양반정식", "꽁치구이, 제육볶음, 5가지 밑반찬 등으로 구성된"),
        ReststopMenu("000483", "영종대교휴게소", "왕돈가스", "넉넉한 양의 두툼한 왕돈가스로 든든한 한 끼"),
        ReststopMenu("000483", "영종대교휴게소", "능이버섯뚝배기불고기", "깊고 풍부한 맛의 능이버섯과 불고기 조합"),
        ReststopMenu("000491", "광주(광주)휴게소", "소머리곰탕", "깊고 진한 국물의 소머리로 만든 전통 곰탕"),
        ReststopMenu("000491", "광주(광주)휴게소", "양곰탕", "부드러운 양고기와 진한 국물이 어우러진 곰탕"),
        )
}
