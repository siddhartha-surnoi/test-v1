package surnoi.FusionIQ.FusionIQ.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfo, Long> {

    static BasicInfo save() {
        return null;
    }
    BasicInfo findByEmail(String email);
    boolean existsByEmail(String email);
//    void deleteHrById(Long id);
    BasicInfo findByEmailAndPassword(String email, String password);
}
 