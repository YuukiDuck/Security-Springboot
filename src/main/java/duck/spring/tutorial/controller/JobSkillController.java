package duck.spring.tutorial.controller;

import duck.spring.tutorial.dto.JobSkillDto;
import duck.spring.tutorial.exception.ResourceNotFoundException;
import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.service.jobskills.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobskills")
@RequiredArgsConstructor
public class JobSkillController {

}