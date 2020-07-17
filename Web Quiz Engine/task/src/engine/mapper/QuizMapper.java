package engine.mapper;

import engine.domain.ActualAnswer;
import engine.domain.Completion;
import engine.domain.Option;
import engine.domain.Quiz;
import engine.dto.CompletionDTO;
import engine.dto.QuizCreationDTO;
import engine.dto.QuizDTO;
import engine.repository.UserRepository;
import engine.security.IAuthenticationFacade;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class QuizMapper {

    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public QuizMapper(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    public Quiz toEntity(QuizCreationDTO quizCreationDTO) {
        Quiz result = new Quiz();
        ArrayList<ActualAnswer> answers;
        if (quizCreationDTO.getAnswer() != null) {
            answers = quizCreationDTO.getAnswer().stream().map(e -> new ActualAnswer(e, result)).collect(Collectors.toCollection(ArrayList::new));
            result.setAnswers(answers);
        } else {
            result.setAnswers(new ArrayList<>());
        }
        result.setOptions(quizCreationDTO.getOptions().stream().map(e -> new Option(e, result)).collect(Collectors.toCollection(ArrayList::new)));
        result.setText(quizCreationDTO.getText());
        result.setTitle(quizCreationDTO.getTitle());
        //System.out.println(authenticationFacade.currentUserEntity().getPassword());
        result.setUser(authenticationFacade.currentUserEntity());
        return result;
    }

    public QuizDTO toDTO(Quiz quiz) {
        QuizDTO result = new QuizDTO();
        result.setId(quiz.getId());
        result.setTitle(quiz.getTitle());
        result.setText(quiz.getText());
        result.setOptions(quiz.getOptions().stream().map(Option::getOption).collect(Collectors.toCollection(ArrayList::new)));
        return result;
    }

    public CompletionDTO toCompletionDTO(Completion completion) {
        CompletionDTO result = new CompletionDTO();
        result.setCompletedAt(completion.getDate().toString());
        result.setId(completion.getQuiz().getId());
        return result;
    }

    public List<QuizDTO> toDTOList(List<Quiz> quizzes) {
        ArrayList<QuizDTO> result = new ArrayList<>();
        for (Quiz quiz:
             quizzes) {
            result.add(toDTO(quiz));
        }
        return result;
    }
}
