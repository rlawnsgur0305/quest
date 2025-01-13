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

	private final HubUserRepository hubUserRepository;
	private final TeamUserRepository teamUserRepository;

	@Override
	public List<UserResponseDTO> getTeamInfoByHubId(List<String> hubIds) {

		List<UserResponseDTO> results = new ArrayList<>();

		for (String hubId : hubIds) {

			if (hubUserRepository.findByHubId(hubId) != null) {
				// HubUserEntity 조회
				List<TeamUserEntity> teams = teamUserRepository.findLatestTeamByHubName(hubId);

				if (!teams.isEmpty()) {
					// 팀 리스트 중 가장 최근 팀 찾기
					TeamUserEntity team = teams.get(0);

					results.add(new UserResponseDTO(team.getHubName(), team.getTeamId(), team.getStatus(),
							team.getCrtDate()));
				}
			}
		}
		return results;
	}
}
