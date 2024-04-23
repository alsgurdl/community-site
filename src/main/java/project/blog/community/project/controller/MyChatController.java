package project.blog.community.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wel")
@Slf4j
public class MyChatController {

    @GetMapping("/chat")
    public String gallery(){
        log.info("/wel/gallery: GET!");

        return "mychat";
    }

}
