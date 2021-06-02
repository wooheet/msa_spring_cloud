package com.example.userservice.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // 스프링 컨텍스트로 사용하기 위해서는 빈으로 등록되어야하는 어노테이션이 필요, 용도가 서비스라던가 레파지토리라던가 등 정해지지 않았으면 편하게 컴포넌트로 등록하면
@Data
public class Greeting {

    @Value("${greeting.message}")
    private String message;

}

