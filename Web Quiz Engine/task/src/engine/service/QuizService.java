package engine.service;

import engine.domain.Feedback;
import engine.domain.Quiz;
import engine.dto.Answer;
import engine.dto.CompletionDTO;
import engine.dto.QuizCreationDTO;
import engine.dto.QuizDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface QuizService {
    Page<QuizDTO> getQuizzes(Integer pageNo, Integer pageSize, String sortBy);
    Page<CompletionDTO> getCompletionsForCurrentUser(Integer pageNo, Integer pageSize, String sortBy);
    QuizDTO createQuiz(QuizCreationDTO quizCreationDTO);
    QuizDTO getQuizById(int id);
    Feedback solveQuiz(int id, Answer answer);
    void deleteByCurrentUser(Long id);
}
