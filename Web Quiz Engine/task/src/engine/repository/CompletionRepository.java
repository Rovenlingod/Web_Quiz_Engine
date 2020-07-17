package engine.repository;

import engine.domain.Completion;
import engine.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, Long> {
    Page<Completion> findAllByUser(User user, Pageable pageable);
}
