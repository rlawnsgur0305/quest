package com.quest.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quest.user.dto.response.UserResponseDTO;
import com.quest.user.entity.TeamUserEntity;
import com.quest.user.repository.HubUserRepository;
import com.quest.user.repository.TeamUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final HubUserRepository hubUserRepository; // 하이브 유저 정보 조회를 위한 Repository
	private final TeamUserRepository teamUserRepository; // 팀 정보를 조회하기 위한 Repository
 
	@Override 
	// hubIds로 전달된 hub_name 들을 기반으로 팀 정보를 조회하고 결과를 리턴하는 함수
	public List<UserResponseDTO> getTeamInfoByHubId(List<String> hubIds) {

		List<UserResponseDTO> results = new ArrayList<>(); // 결과값 담을 리스트

		for (String hubId : hubIds) {

			// 2024_hub_user 테이블에 존재하는 계정인지 먼저 확인 존재 하지 않는 계정이면 건너뜀.
			if (hubUserRepository.findByHubId(hubId) != null) {

				// hub_id와 연결된 Team엔티티를 가져옴
				TeamUserEntity team = teamUserRepository.findLatestTeamByHubName(hubId);

				// 연결된 Team정보가 존재한다면
				if (team != null) {
					// 해당 정보를 ResponseDTO로 새로 생성하고 결과 리스트에 담음.
					results.add(new UserResponseDTO(team.getHubName(), team.getTeamId(), team.getStatus(),
							team.getCrtDate()));
				}
			}
		}
		return results;
	}
}
