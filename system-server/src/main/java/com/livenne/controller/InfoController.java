package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.Consultation;
import com.livenne.service.ConsultationService;
import jakarta.servlet.http.HttpServlet;

import java.util.List;

@Controller("/info")
public class InfoController {

    @GetMapping("/{infoId}")
    public ResponseEntity<?> getInfo(@PathVariable("infoId") Long infoId){
        return ResponseEntity.success();
    }
    @GetMapping("/list")
    public ResponseEntity<?> getList(){
        return ResponseEntity.success();
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParm("kw") String kw){
        return ResponseEntity.success();
    }

}
