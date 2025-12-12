package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.vo.InfoVO;
import com.livenne.service.InfoService;
import com.livenne.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/get/{infoId}")
    public ResponseEntity<InfoVO> getInfo(@PathVariable("infoId") Long infoId){
        if (!infoService.isExistById(infoId)) {
            return ResponseEntity.failureMsg("资讯: %d 不存在", infoId);
        }
        return ResponseEntity.success(new InfoVO(infoService.getById(infoId)));
    }
    @GetMapping("/list")
    public ResponseEntity<List<InfoVO>> getAll(){
        return ResponseEntity.success(infoService.getAll().stream().map(InfoVO::new).toList());
    }
    @GetMapping("/search")
    public ResponseEntity<List<InfoVO>> search(@RequestParm("kw") String kw){
        return ResponseEntity.success(infoService.queryByName(kw).stream().map(InfoVO::new).toList());
    }

}
