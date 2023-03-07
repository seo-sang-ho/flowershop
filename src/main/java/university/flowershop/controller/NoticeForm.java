package university.flowershop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class NoticeForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String loginId;
}