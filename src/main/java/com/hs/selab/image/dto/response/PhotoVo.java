package com.hs.selab.image.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class PhotoVo {
    private MultipartFile Filedate;
    private String callback;
    private String callback_func;
}
