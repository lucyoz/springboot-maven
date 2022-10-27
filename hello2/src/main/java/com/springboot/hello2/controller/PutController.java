package com.springboot.hello2.controller;

import com.springboot.hello2.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController     //이게 붙어있어야 spring이 컨트롤러로 읽음
@RequestMapping("/api/v1/put-api")
public class PutController {

    @RequestMapping(value="/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }

    @PutMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder(); //Builder 패턴

        postData.entrySet().forEach(map->sb.append(map.getKey()+":"+map.getValue()+"\n"));
        return sb.toString();
    }

    @PutMapping("/member2")
    public MemberDto postMember2(@RequestBody MemberDto memberDto){
        return memberDto;
    }

    @PutMapping("/member3")
    public ResponseEntity<MemberDto> putMember(@RequestBody MemberDto memberDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);

    }
}
