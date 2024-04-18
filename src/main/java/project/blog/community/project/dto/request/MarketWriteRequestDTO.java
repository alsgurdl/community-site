package project.blog.community.project.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor

public class MarketWriteRequestDTO {
    private String textWriter;
    private String textTitle;
    private String textContent;
    private LocalDateTime updateDate;
    private int viewCount;


}

