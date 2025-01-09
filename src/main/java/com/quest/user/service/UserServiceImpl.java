package com.quest.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quest.user.dto.response.UserResponseDTO;
import com.quest.user.entity.HubUserEntity;
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
				TeamUserEntity teamInfo = teamUserRepository.findByHubName(hubId);
				
				results.add(new UserResponseDTO(teamInfo.getHubName() , teamInfo.getTeamId() , teamInfo.getStatus() , teamInfo.getCrtDate()));
			} else {
				continue;
			}
		}
	
		return results;
	}
	
}
