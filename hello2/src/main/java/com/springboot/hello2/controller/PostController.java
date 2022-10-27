package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController     //이게 붙어있어야 spring이 컨트롤러로 읽음
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value="/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }

    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder(); //Builder 패턴

        postData.entrySet().forEach(map->sb.append(map.getKey()+":"+map.getValue()+"\n"));
        return sb.toString();
    }

    @PostMapping("/member2")
    public String postMember2(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }
}
