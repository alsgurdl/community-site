package project.blog.community.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.blog.community.project.common.PageMaker;
import project.blog.community.project.common.Search;
import project.blog.community.project.dto.request.DiaryPostRequestDTO;
import project.blog.community.project.dto.request.LikeRequestDTO;
import project.blog.community.project.dto.response.BoardDetailResponseDTO;
import project.blog.community.project.dto.response.BoardMyListResponseDTO;
import project.blog.community.project.dto.response.DiaryDetailResponseDTO;
import project.blog.community.project.dto.response.DiaryListResponseDTO;
import project.blog.community.project.entity.Diary;
import project.blog.community.project.service.BoardService;
import project.blog.community.project.service.DiaryService;
import project.blog.community.util.FileUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static project.blog.community.util.LoginUtils.getCurrentLoginMemberAccount;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Slf4j
public class DiaryController {

    private final BoardService service;
    private final DiaryService diaryService;


    // rootPath = "//ICT4_28/img"
    @Value("${file.upload.root-path}")
    private String rootPath;


    // 마이페이지->다이어리로 이동
    @GetMapping("/diary")
    public String todoDiary(HttpServletRequest request) {
        log.info("/mypage/diary: GET!!!");

        HttpSession session = request.getSession();
        session.getAttribute("login");
        String writer = getCurrentLoginMemberAccount(session);
      return "mypage/diary";
   }


   // 다이어리 -> 할일 저장 요청 (비동기)
    @PostMapping("/diary/todo")
    public ResponseEntity<?> todo(@RequestBody DiaryPostRequestDTO dto, HttpSession session) {
        log.info("/diary/todo: POST 요청! dto: {}", dto);
        diaryService.registerTodo(dto, session);
        return  ResponseEntity.ok().body("success");
    }


    // 다이어리 -> 일기 저장 요청 (비동기)
    @PostMapping("/diary/whatdo")
    public ResponseEntity<?> whatdo(@RequestBody DiaryPostRequestDTO dto, HttpSession session) {
        log.info("/diary/whatdo: POST 요청! dto: {}", dto);
        diaryService.registerWhatdo(dto, session);
        return ResponseEntity.ok().body("success");
    }


    // todoList 날짜별 요청
    @GetMapping("/diary/{regDate}")
    public ResponseEntity<?> todoList(@PathVariable String regDate, HttpSession session) {
        log.info("/diary/{}: GET!!", regDate);

        List<DiaryDetailResponseDTO> todoLists = diaryService.getTodoList(regDate, session);
        log.info("todoList: {}", todoLists);

        return ResponseEntity.ok().body(todoLists);

    }


    // 마이페이지->posting_cube로 이동
    @GetMapping("/posting_cube")
    public String posting_cube(Model model, @ModelAttribute("s") Search page, HttpServletRequest request) {
        System.out.println("search = " + page);
        log.info("/mypage/posting_cube: GET!!!");

        //보여주고 싶은 게시물 리스트
        List<BoardMyListResponseDTO> myList = service.getMyList(page, request);

        // 페이징 버튼 알고리즘 적용 -> 사용자가 요청한 페이지 정보, 총 게시물 개수를 전달.
        // 페이징 알고리즘 자동 호출.
        PageMaker pageMaker = new PageMaker(page, service.getCount(page, request));


        model.addAttribute("bList", myList);
        model.addAttribute("maker", pageMaker);

        log.info(myList.toString());

        return "mypage/posting_cube";
    }


   // 큐브 게시물 누르면 그 게시물로 이동
   @GetMapping("/posting_cube/{bno}")
   public String detail(@PathVariable("bno") int bno, @ModelAttribute("s") Search search, Model model) {
      System.out.println("/board/detail: GET! " + bno);
      BoardDetailResponseDTO dto = service.getDetail(bno);

      model.addAttribute("b", dto);
      return "home/detail";

   }


   // 글 작성하기 누르면 새로운 글 작성하는 페이지로 이동
   @GetMapping("/newposting")
   public String newposting() {
      log.info("/mypage/newposting: GET!!!");
      return "mypage/newposting";
   }


    // 글쓰기 제출 페이지 (DTO 안쓰고)
    @PostMapping("/newposting")
    public String writeSubmit(@RequestParam @Param("category") String category,
                              @RequestParam ("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("file")
                              MultipartFile uploadedImage,
                              HttpServletRequest request) {

        log.info("/mypage/newposting: POST, {}, {}, {}", category, title, content);
        log.info("file-name: {}", uploadedImage.getOriginalFilename());
        log.info("file-size: {}KB", uploadedImage.getSize() / 1024.0); // getSize()는 MB 단위
        log.info("file-type: {}", uploadedImage.getContentType());

        // 세션에서 자신의 account 가져오기
        HttpSession session = request.getSession();
        session.getAttribute("login");
        String writer = getCurrentLoginMemberAccount(session);

        // 서버에 파일 업로드 지시
        String savePath = null;

        if (uploadedImage.getSize() == 0) {
            savePath = null;
        } else {
            savePath = FileUtils.uploadFile(uploadedImage, rootPath);
        }
        log.info("save-path: {}", savePath);


        // board table 에 게시글 저장하기: writer, title, content, file-image (파일 경로), category
        service.saveBoard(category, title, content, savePath, writer);

        return "redirect:/mypage/posting_cube";
    }


    // 글 삭제 요청 (/mypage/delete : GET)
    // 글번호 전달되면 삭제 진행
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("mypage/posting_cube/delete: GET!! " + bno);
        service.delete(bno);

        return "redirect:/mypage/posting_cube";
    }


    // 좋아요 버튼
    @PostMapping("/posting_cube/like/{bno}")
    @ResponseBody
    public ResponseEntity<Integer> like(@PathVariable("bno") int bno, @RequestBody LikeRequestDTO dto,
                                        HttpServletRequest request, Model model) {
        log.info("/mypage/posting_cube/like/{bno}: POST: {}, {}", dto.getBno(), dto.getNumber());

        // 좋아요 이미 눌렀는지 확인하기
        int like = service.checkLike(request, bno);

        if (like > 0) { // 이미 좋아요를 눌렀다면
            model.addAttribute("l", 1);
        } else { // 좋아요를 누르지 않았다면
            model.addAttribute("l", 0);
        }

        // 좋아요 수 1 증가 또는 1 감소시키기
        int isCookie = service.changeLike(dto, request);
        return ResponseEntity.ok().body(isCookie);
    }




}

