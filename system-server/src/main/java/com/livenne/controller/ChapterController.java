package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.PathVariable;

@Controller("/chapter")
public class ChapterController {
    @GetMapping("/{chapter}")
    public ResponseEntity<?> getChapter(@PathVariable("chapter") String chapter){
        return ResponseEntity.success();
    }
}
