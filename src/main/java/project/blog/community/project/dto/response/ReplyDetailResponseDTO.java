package project.blog.community.project.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import project.blog.community.project.entity.Reply;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDetailResponseDTO {

   private int rno;
   private String text;
   private String writer;

   // DTO가 JSON으로 변환될 때 원하는 Format 형식으로 자동 변환
   @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
   private LocalDateTime regDate;

   // 엔터티 변환
   public ReplyDetailResponseDTO(Reply reply) {
      this.rno = reply.getReplyNo();
      this.text = reply.getContent();
      this.writer = reply.getReplyWriter();
      this.regDate = reply.getReplyDate();
   }

}








































