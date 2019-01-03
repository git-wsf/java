package com.yangliuxin.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VivoResponse {

    private String userSessionId;

    private Long totalSize = null;

    private Long totalPage = null;

    private Boolean nextPage = null;

    private String expResult;

    private String errorCode = "-1";

    private String errorMsg = "";

    private String dataExt;







}
