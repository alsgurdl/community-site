package project.blog.community.project.entity;

/*
User 테이블 MYSQL 복사 붙기!

CREATE TABLE tbl_user (
   account_number VARCHAR(50),
   password VARCHAR(150) NOT NULL,
   name VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE,
   auth VARCHAR(20) DEFAULT 'COMMON',
   reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
   birthday int NOT NULL,
   gender VARCHAR(20) NOT NULL,
   nickname VARCHAR(45) NOT NULL,
   report int DEFAULT 0,

   CONSTRAINT pk_member PRIMARY KEY (account_number)
);

*/


import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

   // 회원 아이디
   private String accountNumber;
   // 회원 비밀번호
   private String password;
   // 회원 이름
   private String name;
   // 회원 이메일
   private String email;
   // 회원 권한
   private Auth auth;
   // 회원 가입 일자
   private LocalDateTime regDate;

   private Gender gender;

   private String nickname;

   // 회원 생일(year)
   private int birthday;

   // 신고 횟수
   private int report;

   // 쿠키
   private String sessionId;
   // 쿠키 수명
   private LocalDateTime limitTime;

   // 회원 프사
   private String profilePicture;

   // 회원 가입 방법
   private LoginMethod loginMethod;



}












































