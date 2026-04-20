package com.example.scheduledevelop.user.service;

import com.example.scheduledevelop.user.dto.UserCreateRequest;
import com.example.scheduledevelop.user.dto.UserCreateResponse;
import com.example.scheduledevelop.user.dto.UserGetResponse;
import com.example.scheduledevelop.user.dto.UserPutRequest;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        User user = new User(request.getUserName(), request.getEmail(),request.getPassword());
        User saveUser = userRepository.save(user);
        return new UserCreateResponse(
                saveUser.getId(),
                saveUser.getUserName(),
                saveUser.getEmail(),
                saveUser.getCreateAt(),
                saveUser.getModifiedAt()
        );

    }

    @Transactional
    public List<UserGetResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<UserGetResponse> dtos = new ArrayList<>();
        for (User user : users) {
            UserGetResponse dto = new UserGetResponse(
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getCreateAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UserGetResponse getOneUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("유저가 존재하지 않습니다.")
        );
        return new UserGetResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public UserGetResponse putOneUser(Long id, UserPutRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Id에 해당하는 유저는 없습니다.")
        );
        user.updateUser(request.getUserName(), request.getEmail());
        return new UserGetResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreateAt(),
                user.getModifiedAt()
        );
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Id에 해당하는 유저는 없습니다.")
        );
        userRepository.deleteById(id);

    }
}
