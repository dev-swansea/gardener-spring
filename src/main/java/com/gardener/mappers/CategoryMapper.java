package com.gardener.mappers;

import java.util.List;

import com.gardener.domain.Search;

public interface CategoryMapper {
	
	public List<Search> searchCategory(String category);
}
