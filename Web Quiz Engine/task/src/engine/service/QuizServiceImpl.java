package engine.service;

import engine.domain.ActualAnswer;
import engine.domain.Completion;
import engine.dto.Answer;
import engine.domain.Feedback;
import engine.domain.Quiz;
import engine.dto.CompletionDTO;
import engine.dto.QuizCreationDTO;
import engine.dto.QuizDTO;
import engine.exceptions.QuizForbiddenException;
import engine.exceptions.QuizNotFoundException;
import engine.exceptions.QuizNotValidException;
import engine.mapper.QuizMapper;
import engine.repository.CompletionRepository;
import engine.repository.QuizRepository;
import engine.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService{

    private QuizMapper quizMapper;
    private QuizRepository quizRepository;
    private IAuthenticationFacade authenticationFacade;
    private CompletionRepository completionRepository;

    @Autowired
    public QuizServiceImpl(QuizMapper quizMapper,
                           QuizRepository quizRepository,
                           IAuthenticationFacade authenticationFacade,
                           CompletionRepository completionRepository) {
        this.quizMapper = quizMapper;
        this.quizRepository = quizRepository;
        this.authenticationFacade = authenticationFacade;
        this.completionRepository = completionRepository;
    }

    public Page<QuizDTO> getQuizzes(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return new PageImpl<>(quizRepository.findAll(paging).getContent().stream().map(quizMapper::toDTO).collect(Collectors.toCollection(ArrayList::new)),
                paging,
                quizRepository.count());
    }

    public QuizDTO createQuiz(QuizCreationDTO quizCreationDTO) {

        if (quizCreationDTO.getOptions() == null || quizCreationDTO.getOptions().size() < 2) throw new QuizNotValidException("Quiz must contain at least 2 options");
        if (StringUtils.isEmpty(quizCreationDTO.getTitle())) throw new QuizNotValidException("Quiz must have a title");
        if (StringUtils.isEmpty(quizCreationDTO.getText())) throw new QuizNotValidException("Quiz must have a text");

        Quiz quiz = quizMapper.toEntity(quizCreationDTO);
        return quizMapper.toDTO(quizRepository.save(quiz));
    }

    @Override
    public QuizDTO getQuizById(int id) {
        Optional<Quiz> quiz = quizRepository.findById((long) id);
        if (quiz.isEmpty()) throw new QuizNotFoundException("Quiz not found for id = " + id);
        return quizMapper.toDTO(quiz.get());
    }

    @Override
    public Feedback solveQuiz(int id, Answer answer) {
        Optional<Quiz> quiz = quizRepository.findById((long) id);
        if (quiz.isEmpty()) throw new QuizNotFoundException("Quiz not found for id = " + id);

        ArrayList<Integer> guess = answer.getAnswer();
        ArrayList<Integer> actualAnswer = quiz.get().getAnswers().stream().map(ActualAnswer::getAnswer).collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(guess);
        Collections.sort(actualAnswer);
        if (guess.equals(actualAnswer)) {

            Completion registeredCompletion = new Completion();
            registeredCompletion.setUser(authenticationFacade.currentUserEntity());
            registeredCompletion.setQuiz(quiz.get());
            registeredCompletion.setDate(new Date());
            completionRepository.save(registeredCompletion);

            return new Feedback(true, "Congratulations, you're right!");
        } else {
            return new Feedback(false, "Wrong answer! Please, try again.");
        }
    }

    @Override
    public Page<CompletionDTO> getCompletionsForCurrentUser(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        List<Completion> completions = completionRepository.findAllByUser(authenticationFacade.currentUserEntity(), paging).getContent();
        completions.forEach(e -> System.out.println(e.getQuiz().getId()));
        return new PageImpl<>(completionRepository
                .findAllByUser(authenticationFacade.currentUserEntity(), paging)
                .getContent()
                .stream().map(quizMapper::toCompletionDTO).collect(Collectors.toCollection(ArrayList::new)),
                paging,
                completions.size());
    }

    @Transactional
    @Override
    public void deleteByCurrentUser(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isEmpty()) throw new QuizNotFoundException("Quiz not found for id = " + id);
        if (!quiz.get().getUser().equals(authenticationFacade.currentUserEntity())) throw new QuizForbiddenException("You are not the author of the quiz with id = " + id);
        quizRepository.deleteByUserAndId(authenticationFacade.currentUserEntity(), id);
    }
}