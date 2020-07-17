package engine.controller;

import engine.dto.Answer;
import engine.domain.Feedback;
import engine.dto.QuizCreationDTO;
import engine.dto.QuizDTO;
import engine.service.QuizService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

//    @GetMapping("/quiz")
//    public Quiz getTestQuiz() {
//        return quizService.getQuizzes().get(0);
//    }
//
//    @PostMapping("/quiz")
//    public Feedback testAnswer(Answer answer) {
//        return quizService.getTestFeedBack(answer);
//    }

    @PostMapping
    public ResponseEntity createQuiz(@RequestBody QuizCreationDTO quizCreationDTO) {
        return ResponseEntity.ok(quizService.createQuiz(quizCreationDTO));
    }

    @PostMapping(value = "/{quiz_id}/solve" /* , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE */)
    public ResponseEntity solveQuiz(@PathVariable(value = "quiz_id") int id, @RequestBody Answer answer) {
        Feedback feedback = quizService.solveQuiz(id, answer);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/{quiz_id}")
    public ResponseEntity getQuizById(@PathVariable(value = "quiz_id") int quizId) {
        QuizDTO result = quizService.getQuizById(quizId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{quiz_id}")
    public ResponseEntity deleteQuizById(@PathVariable(value = "quiz_id") Long quizId) {
        quizService.deleteByCurrentUser(quizId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity getQuizzes(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(quizService.getQuizzes(page, pageSize, sortBy));
    }

    @GetMapping("/completed")
    public ResponseEntity getCompletionsForCurrentUser(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                     @RequestParam(defaultValue = "date") String sortBy) {
        return ResponseEntity.ok(quizService.getCompletionsForCurrentUser(page, pageSize, sortBy));
    }
}
