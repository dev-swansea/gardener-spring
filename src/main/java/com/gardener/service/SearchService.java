package com.gardener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.domain.Search;
import com.gardener.mappers.SearchMapper;

@Service
public class SearchService {

    @Autowired
    private SearchMapper mapper;
    
    public List<Search> searchTitle(String text) {
    	return mapper.searchTitle(text);
    }
    
    public List<Search> searchName(String text) {
    	return mapper.searchName(text);
    }
    
    public List<Search> searchContent(String text) {
    	return mapper.searchContent(text);
    }

    public List<Search> searchAll(String text) {
    	return mapper.searchAll(text);
    }
    
    public List<Search> magazine(){
    	return mapper.magazine();
    }
    
    
    
    
    
    
    
    
    
}
