package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.dto.request.QnaRequestDTO;
import com.example.demo.dto.response.QnaDetailResponseDTO;
import com.example.demo.dto.response.QnaListResponseDTO;
import com.example.demo.entity.Qna;
import com.example.demo.repository.QnaRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaService {

    private final UserRepository userRepository;
    private final QnaRepository qnaRepository;

    public QnaListResponseDTO create(QnaRequestDTO requestDTO) {
      //  User user = getUser(userId);

        qnaRepository.save(requestDTO.toEntity());
        log.info("qna 작성 완료! qna 제목 : {}", requestDTO.getQTitle());
        log.info("qna 작성 완료! qna 내용 : {}", requestDTO.getQContent());

        return retrieve();
    }

    // qna 목록 가져오기
    public QnaListResponseDTO retrieve(){
        List<Qna> entityList  = qnaRepository.findAll();

        List<QnaDetailResponseDTO> dtoList = entityList.stream()
                .map(QnaDetailResponseDTO::new)
                .toList();

        return QnaListResponseDTO.builder()
                .qnas(dtoList)
                .build();
    }

    private User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("회원 정보가 없습니다.")
        );
        return user;
    }
}