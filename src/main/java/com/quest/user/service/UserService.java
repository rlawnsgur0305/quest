package com.quest.user.service;

import java.util.List;

import com.quest.user.dto.response.UserResponseDTO;

public interface UserService {
	
	public List<UserResponseDTO> getTeamInfoByHubId (List<String> hubIds);
	
}
