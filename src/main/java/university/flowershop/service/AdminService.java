package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Address;
import university.flowershop.domain.Admin;
import university.flowershop.domain.Member;
import university.flowershop.repository.AdminRepository;

import javax.annotation.PostConstruct;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;

//    @PostConstruct
//    public void initAdmin() {
//        Admin admin = new Admin();
//        admin.setId(1L);
//        admin.setLoginId("admin");
//        admin.setPassword("1234");
//
//        adminRepository.save(admin);
//
//        log.info("사용자아이디 추가되는 로그");
//    }
}
