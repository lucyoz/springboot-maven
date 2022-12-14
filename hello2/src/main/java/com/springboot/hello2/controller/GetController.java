package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController     //이게 붙어있어야 spring이 컨트롤러로 읽음
@RequestMapping("/api/v1/get-api")
@Slf4j
public class GetController {

    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String hello(){
        log.info("hello로 요총이 들어옴");
        return "Hello World";
    }

    @GetMapping(value="/name")
    public String getName(){
        log.info("getName으로 요청이 들어옴");
        return "kwon";
    }

    @GetMapping(value="/variable1/{variable}")
    public String getVariable(@PathVariable String variable){
        log.info("getVariable1으로 요청이 들어옴 variable:{}",variable);
        return variable;
    }

    @GetMapping(value="/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }
//10월 27일

    @GetMapping(value="/request1")
    public String getRequestParam1(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam String organization){
        return  name+" "+email+" "+organization;
    }

    @GetMapping(value="/request2")
    public String getReqestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map->{
            sb.append(map.getKey()+" : "+map.getValue()+"\n");
        });
        return sb.toString();
    }

    @GetMapping(value="/request3")
    public String getRequestParam(MemberDto memberDto){
        return memberDto.toString();
    }



}
