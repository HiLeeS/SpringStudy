package com.example.spring1.service;

import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.User;
import com.example.spring1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    //서비스 선언    (서비스 객체를 스프링 부트에 선언)
public class UserService {
    @Autowired  //DI
    private UserRepository userRepository;

    public User create(UserForm dto) {
        User user = dto.toEntity();
        if(user.getId() != null){
            return null;
        }
        return userRepository.save(user);
    }

    public User login(UserForm dto) {
        User user = dto.toEntity(); //사용자가 입력한 정보를 갖는 객체
        User login = userRepository.findByEmailAddress(user.getEmail(), user.getPassword()); //이메일을 건내서 해당 객체 반환
        if(login==null){
            return null;
        }
        return login;
    }

    public User emailCheck(String email) {
        User check = userRepository.findByEmail(email);
        if(check != null){
            return null;
        }
        return check;
    }

    public User update(Long id, UserForm dto) {
        //1. 수정용 엔티티 생성
        User user = dto.toEntity();
        log.info("id: {}, article: {}", id, user.toString());

        //2. 대상 엔티티 찾기
        User target = userRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리(대상이 없거나 id가 다른 경우)
        if (target == null || id != user.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, user.toString());
            return null;
        }
        //4. 업데이트
        target.patch(user);
        User updated = userRepository.save(target);
        return updated;
    }

    public User delete(Long id) {
        //대상 찾기
        User target = userRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if(target == null){
            return null;
        }

        //대상 삭제
        userRepository.delete(target);
        return target;
    }

    @Transactional  //해당 메소드
    // 를 트랜잭션으로 묶는다.
    public List<User> createArticles(List<UserForm> dtos) {
        //dto 묶음을 entity 묶음으로 변환
        List<User> userList =  dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        //entity 묶음을 DB로 저장
        userList.stream()
                .forEach(user -> userRepository.save(user));

        //강제 예외 발생
        userRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패!")
        );

        //결과값 반환
        return userList;

    }
}
