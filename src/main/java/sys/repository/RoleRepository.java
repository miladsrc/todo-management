package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
