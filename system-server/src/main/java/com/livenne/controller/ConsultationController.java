package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.Controller;
import com.livenne.annotation.GetMapping;
import com.livenne.annotation.RequestParm;
import com.livenne.common.model.Consultation;
import com.livenne.service.ConsultationService;
import com.livenne.service.impl.ConsultationServiceImpl;
import jakarta.servlet.http.HttpServlet;

@Controller("/consultation")
public class ConsultationController extends HttpServlet {
    public ConsultationService consultationService = ConsultationServiceImpl.instance;

    @GetMapping
    public ResponseEntity<?> getConsultation(@RequestParm("consultationId") Long consultationId) {
        Consultation consultation = consultationService.get(consultationId);
        if (consultation == null) return ResponseEntity.failureMsg("");
        return ResponseEntity.success(consultation);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getConsultationList() {
        return ResponseEntity.success(consultationService.getList());
    }

}
