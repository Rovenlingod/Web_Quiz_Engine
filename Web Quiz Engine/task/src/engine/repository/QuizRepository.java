package engine.repository;


import engine.domain.Quiz;
import engine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Transactional
    void deleteByUserAndId(User user, Long id);
}
