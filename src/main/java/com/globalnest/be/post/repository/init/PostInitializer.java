package com.globalnest.be.post.repository.init;

import com.globalnest.be.global.util.DummyDataInit;
import com.globalnest.be.post.domain.Post;
import com.globalnest.be.post.repository.PostRepository;
import com.globalnest.be.user.domain.User;
import com.globalnest.be.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

@Slf4j
@RequiredArgsConstructor
@Order(2)
@DummyDataInit
public class PostInitializer implements ApplicationRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (postRepository.count() > 0) {
            log.info("[Post]더미 데이터 존재");
        } else {
            User DUMMY_ADMIN = userRepository.findById(1L).orElseThrow();
            User DUMMY_USER1 = userRepository.findById(2L).orElseThrow();
            User DUMMY_USER2 = userRepository.findById(3L).orElseThrow();

            List<Post> postList = new ArrayList<>();

            Post REAL_POST1 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("한국에서의 첫 겨울, 그리고 고립")
                    .content("한국에서의 첫 겨울이 다가오고 있습니다. 날씨는 점점 더 추워지고, 나는 아직 적응이 되지 않은 것 같습니다. 농촌에서 일하는 외국인으로서, 일은 힘들지만 그보다는 더 힘든 것이 혼자라는 느낌입니다. 아침 일찍 일터로 가기 전에, 아직 어두운 하늘 아래서 혼자 걷는 길이 너무 외롭습니다. 한국어가 서툴러서 인사를 하려고 해도 말이 잘 나오지 않습니다. 가끔은 지나가는 사람들의 표정이 나를 무시하는 것처럼 느껴져서 더 힘들어요.\n"
                            + "\n"
                            + "오늘도 마트에 가서 밥을 사야 했습니다. 다른 사람들이 하는 것처럼, 식당에서 한국 음식을 먹고 싶었지만 아직 내 입맛에 맞는 음식을 찾는 게 어렵습니다. 매운 음식은 너무 맵고, 밥을 먹을 때마다 마음속 깊은 곳에서 '집에 있는 가족들과 함께 먹는 밥이 그리워' 하는 생각이 떠오릅니다. 한국에서는 말이 잘 통하지 않아서 집을 떠나 온 이유를 쉽게 설명할 수도 없고, 그렇다고 누구에게 털어놓을 수도 없습니다.\n"
                            + "\n"
                            + "오늘 하루도 지나고 나면, 내일도 또 반복될 것 같아 걱정이 됩니다. 하지만 조금씩 더 배워가고, 익숙해지겠죠. 일주일에 한 번씩 연락하는 가족들의 목소리가 오늘도 위로가 되었습니다. 그들이 응원하는 것처럼, 나도 내일을 위해 조금 더 힘을 내야겠다는 생각이 듭니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST2 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("외로움과 고립: 외국인 노동자들의 감정적 지원 필요성")
                    .content("많은 외국인 노동자들이 일상적인 외로움과 사회적 고립을 겪고 있습니다. 특히 가족과 떨어져 지내는 노동자들은 감정적으로 큰 부담을 느끼며, 이는 종종 정신 건강 문제로 이어질 수 있습니다. 외로움은 장기적으로 노동자의 직무 만족도를 낮추고, 생산성에도 악영향을 미칠 수 있습니다.\n"
                            + "\n"
                            + "외국인 노동자들을 위한 감정적 지원이 필수적입니다. 기업과 지역 사회가 협력하여 정기적인 상담 서비스, 사회적 네트워크 형성, 그리고 문화적인 교류 프로그램을 지원해야 합니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST3 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("제조업 외국인 노동자의 언어 문제 해결을 위한 방안")
                    .content("한국의 제조업에 종사하는 외국인 노동자들은 언어 문제로 인해 업무 효율성이 떨어지고, 종종 직장에서의 불편을 겪습니다. 안전 사고를 예방하고 효율적인 작업을 위해서는 기본적인 언어 교육과 번역 시스템이 필수적입니다.\n"
                            + "\n"
                            + "언어 교육은 단순히 일상적인 소통을 넘어서, 노동자들의 안전과 직무 수행에 직접적인 영향을 미칩니다. 따라서 기업 차원에서 체계적인 언어 교육 프로그램과 다국어 지원 시스템을 마련해야 하며, 정부와 기업의 협력이 더욱 강화되어야 합니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST4 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("농촌에서 일하는 외국인 노동자의 문화적 고립 문제")
                    .content("한국 농촌에서 일하는 외국인 노동자들은 종종 문화적 고립을 겪고 있습니다. 언어의 장벽뿐만 아니라, 농촌 지역의 특성상 외국인 노동자들이 사회적 연결망을 형성하는 것이 어려운 현실입니다. 외로움과 불안감은 종종 정신 건강 문제로 이어지며, 이는 장기적으로 노동력의 질과 안전에 영향을 미칠 수 있습니다.\n"
                            + "\n"
                            + "이를 해결하기 위해 농촌 지역에서도 외국인 노동자들을 위한 문화 교류 프로그램이나 사회적 지원 시스템이 절실히 필요합니다. 또한, 언어 교육과 지역 사회와의 적극적인 소통을 통해 고립을 줄이고, 그들의 사회적 통합을 돕는 노력이 중요합니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST5 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("오늘도 일터로, 고단한 하루")
                    .content("오늘은 일터에서 하루 종일 땀을 흘렸습니다. 몸은 힘들지만, 나름 보람을 느낍니다. 저녁에는 집에 돌아와 편안하게 쉴 수 있는 시간이 그나마 위안입니다. 그래도 혼자 집에 들어가면 왠지 모르게 외로움을 느껴요. 한국어가 서툴러 친구를 사귀는 것도 어렵고, 그저 일만 반복되는 삶이 조금은 지칩니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST6 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("가족이 그리운 밤")
                    .content("한국에 온 지 6개월이 됐습니다. 요즘 들어 가족이 그리워지는 날들이 많아요. 매일 아침 저녁으로 일만 하고, 잠깐의 여유도 없이 시간을 보내다 보니 가족의 얼굴이 자주 떠오릅니다. 전화로만 대화할 수 있지만, 그들의 목소리가 그리울 때마다 마음이 아파요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST7 = Post.builder()
                    .user(DUMMY_ADMIN)
                    .title("외로움 속에서 찾은 작은 기쁨")
                    .content("매일 반복되는 일상 속에서 작은 기쁨을 찾기 위해 노력합니다. 오늘은 점심시간에 근처 공원에 가서 잠깐 산책을 했습니다. 혼자 걷는 길에서, 날씨가 좋다는 생각에 잠시나마 기분이 좋아졌어요. 그나마 이런 작은 일들이 나를 버티게 해주는 것 같습니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST8 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("언어의 장벽, 그리고 어려운 대화")
                    .content("오늘 점심시간에 동료들과 이야기를 나누려고 했습니다. 하지만 한국어가 서툴러서 무슨 말을 하고 있는지 잘 이해하지 못했습니다. 그럴 때마다 내가 여기서 혼자라는 느낌이 들어서 마음이 힘듭니다. 더 빨리 한국어를 배우고 싶지만, 너무 어려워서 조금씩만 할 수 있을 뿐이에요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST9 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("일상 속 작은 발견들")
                    .content("오늘은 일하는 중간에 길을 지나가다 꽃이 피어 있는 것을 봤습니다. 아무것도 모르고 지나쳤을 텐데, 그 꽃을 보고 잠시 멈춰 서서 기분이 조금 나아졌어요. 이런 작은 것들이 내 일상 속에서 위안이 되네요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST10 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("한국 음식에 도전!")
                    .content("오늘은 처음으로 한국 음식을 시도해봤습니다. 김치찌개를 만들어 봤는데, 맵고 자극적인 맛이 처음에는 힘들었지만, 점차 적응되고 있는 것 같아요. 아직은 많이 어색하지만, 한국 문화를 배우는 기분이 듭니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST11 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("일상 속 작은 발견들")
                    .content("오늘은 일하는 중간에 길을 지나가다 꽃이 피어 있는 것을 봤습니다. 아무것도 모르고 지나쳤을 텐데, 그 꽃을 보고 잠시 멈춰 서서 기분이 조금 나아졌어요. 이런 작은 것들이 내 일상 속에서 위안이 되네요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST12 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("혼자 보내는 첫 명절")
                    .content("오늘은 설날입니다. 그런데 한국에 온 이후로 처음 맞이하는 명절이라 많이 서운해요. 집에 있는 가족들과 함께 보내는 명절이 그리워서 마음이 무겁습니다. 한국에서의 명절 분위기도 좋은데, 제겐 조금 낯설고 외로워요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST13 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("외국인으로서 느끼는 차별")
                    .content("한국에서 일을 하다 보면 가끔씩 차별을 느낄 때가 있습니다. 어떤 사람들은 나를 다른 사람들과 다르게 대하는 것 같아요. 그런 때마다 외국인으로서의 존재가 낯설고 불편하게 느껴집니다. 그래도 그런 마음을 가지지 않으려고 노력하고 있습니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST14 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("혼자 보내는 첫 명절")
                    .content("오늘은 설날입니다. 그런데 한국에 온 이후로 처음 맞이하는 명절이라 많이 서운해요. 집에 있는 가족들과 함께 보내는 명절이 그리워서 마음이 무겁습니다. 한국에서의 명절 분위기도 좋은데, 제겐 조금 낯설고 외로워요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST15 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("매일 일만 하는 삶")
                    .content("오늘도 일터에서 하루를 보냈습니다. 반복되는 일상이 지겹기도 하지만, 그래도 일을 통해 조금씩 한국에 익숙해져 가는 것 같아요. 가끔은 일을 끝내고 집에 돌아가는 길에 너무 지쳐서 아무것도 하기 싫을 때가 있습니다. 그래도 내일을 위해 힘을 내야겠죠.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST16 = Post.builder()
                    .user(DUMMY_USER1)
                    .title("한국에서의 첫 겨울")
                    .content("겨울이 오고 있습니다. 한국의 겨울은 너무 춥네요. 첫 겨울이라 걱정했는데, 오늘은 눈이 조금 내렸습니다. 처음 보는 눈이 신기하지만, 몸은 너무 얼어붙어서 겨울을 어떻게 버텨야 할지 모르겠어요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST17 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("한국어 공부의 어려움")
                    .content("한국어 공부가 정말 힘들어요. 특히 발음이 어렵고, 듣기도 잘 안 들립니다. 가끔은 너무 답답해서 공부가 재미없어질 때가 있지만, 그래도 조금씩 배워가고 있다는 생각에 위안을 삼습니다. 조금 더 인내심을 가지고 공부해야겠죠.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST18 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("혼자 먹는 밥, 외로움의 시간")
                    .content("오늘은 혼자서 밥을 먹었어요. 한국에서 친구들을 사귀는 게 어렵지만, 그나마 직장에서 같이 밥을 먹는 동료들이 있어 조금은 위로가 됩니다. 그래도 혼자 먹는 밥은 여전히 외롭고 쓸쓸합니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST19 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("한국에서의 첫 겨울")
                    .content("겨울이 오고 있습니다. 한국의 겨울은 너무 춥네요. 첫 겨울이라 걱정했는데, 오늘은 눈이 조금 내렸습니다. 처음 보는 눈이 신기하지만, 몸은 너무 얼어붙어서 겨울을 어떻게 버텨야 할지 모르겠어요.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST20 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("주말의 고요함")
                    .content("주말이 되면 한 주 동안의 피로가 몰려옵니다. 일은 없지만, 집에 혼자 있는 시간은 너무 고요하고 적적해요. 한국에 와서 처음 맞이하는 주말이지만, 다른 사람들과 함께 보내는 시간도 그리워집니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST21 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("작은 대화의 즐거움")
                    .content("오늘은 회사에서 동료들과 간단한 대화를 나눴습니다. 한국어가 서툴지만, 조금씩 나아지는 게 느껴져서 뿌듯했어요. 대화가 짧았지만, 나를 이해해주는 사람이 있다는 게 정말 감사하게 느껴졌습니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST22 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("외국인으로서의 어려움")
                    .content("한국에서 생활하는 것이 생각보다 많이 어려운 것 같습니다. 한국어도 어렵고, 문화도 다르고, 모든 것이 낯설어요. 하지만 그 모든 어려움을 극복할 때마다 조금씩 성장하는 것 같아서 기쁩니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST23 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("한국에 온 이유")
                    .content("한국에 온 이유는 더 나은 생활을 위해서였습니다. 하지만 여기서 겪는 어려움이 많아서 가끔은 돌아가고 싶은 생각이 듭니다. 그래도 이곳에서 배워가는 것들이 많아서 계속 버티고 있습니다.")
                    .postImageUrl("")
                    .build();

            Post REAL_POST24 = Post.builder()
                    .user(DUMMY_USER2)
                    .title("외로움과의 싸움")
                    .content("오늘도 혼자서 하루를 마쳤습니다. 외로움은 여전히 나를 괴롭히고 있지만, 그 속에서도 작은 기쁨을 찾으려고 노력하고 있습니다. 매일 혼자서 저녁을 먹으면서도 언젠가는 더 많은 사람들과 이야기할 수 있기를 바라며 하루를 마감합니다.")
                    .postImageUrl("")
                    .build();

            postList.add(REAL_POST1);
            postList.add(REAL_POST2);
            postList.add(REAL_POST3);
            postList.add(REAL_POST4);
            postList.add(REAL_POST5);
            postList.add(REAL_POST6);
            postList.add(REAL_POST7);
            postList.add(REAL_POST8);
            postList.add(REAL_POST9);
            postList.add(REAL_POST10);
            postList.add(REAL_POST11);
            postList.add(REAL_POST12);
            postList.add(REAL_POST13);
            postList.add(REAL_POST14);
            postList.add(REAL_POST15);
            postList.add(REAL_POST16);
            postList.add(REAL_POST17);
            postList.add(REAL_POST18);
            postList.add(REAL_POST19);
            postList.add(REAL_POST20);
            postList.add(REAL_POST21);
            postList.add(REAL_POST22);
            postList.add(REAL_POST23);
            postList.add(REAL_POST24);

            postRepository.saveAll(postList);
        }
    }
}
