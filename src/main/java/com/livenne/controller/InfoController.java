package com.livenne.controller;

import io.github.livenne.ResponseEntity;
import com.livenne.common.model.vo.InfoVO;
import com.livenne.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import io.github.livenne.annotation.container.*;
import io.github.livenne.annotation.servlet.*;

import java.util.List;

@Slf4j
@Controller("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/get/{infoId}")
    public ResponseEntity getInfo(@PathVariable("infoId") Long infoId){
        if (!infoService.isExistById(infoId)) {
            return ResponseEntity.notFound().message("资讯: %d 不存在", infoId);
        }
        return ResponseEntity.ok(new InfoVO(infoService.getById(infoId)));
    }
    @GetMapping("/list")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(infoService.getAll().stream().map(InfoVO::new).toList());
    }
    @GetMapping("/search")
    public ResponseEntity search(@RequestParm("kw") String kw){
        return ResponseEntity.ok(infoService.queryByName(kw).stream().map(InfoVO::new).toList());
    }

}
