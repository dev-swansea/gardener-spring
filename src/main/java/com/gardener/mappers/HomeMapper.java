package com.gardener.mappers;

import java.util.List;

import com.gardener.domain.Search;

public interface HomeMapper {
	
	public List<Search> FavoriteTop();
	
	public List<Search> TopCollection();

	
}
