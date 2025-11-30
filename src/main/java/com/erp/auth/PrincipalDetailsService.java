package com.erp.auth;

import com.erp.dao.ManagerDAO;
import com.erp.dto.ManagerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final ManagerDAO managerDAO;


    @Override
    public UserDetails loadUserByUsername(String managerId) throws UsernameNotFoundException {
        System.out.println("===== 로그인 시도 =====");
        System.out.println("입력된 managerId = " + managerId);
        ManagerDTO manager = managerDAO.getManagerForLogin(managerId);
        System.out.println("조회된 매니저 = " + manager);
        if(manager == null){
            throw new UsernameNotFoundException("해당 아이디가 존재하지 않습니다: " + managerId);
        } // 나중에 확인

        return new PrincipalDetails(manager);
    }
}
