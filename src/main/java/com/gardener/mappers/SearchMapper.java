package com.gardener.mappers;

import java.util.List;

import com.gardener.domain.Search;

public interface SearchMapper {
	
	public List<Search> searchTitle(String text);
	
	public List<Search> searchName(String text);
	
	public List<Search> searchContent(String text);
	
	public List<Search> searchAll(String text);
	
	public List<Search> magazine();
	
	
}
