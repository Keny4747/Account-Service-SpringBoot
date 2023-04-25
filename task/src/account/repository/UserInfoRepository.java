package account.repository;

import account.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmailIgnoreCase(String email);

    Optional<UserInfo> findByName(String name);
}
