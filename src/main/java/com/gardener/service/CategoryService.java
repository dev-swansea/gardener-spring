package com.gardener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.domain.Search;
import com.gardener.mappers.CategoryMapper;
import com.gardener.mappers.SearchMapper;

@Service
public class CategoryService {
	 @Autowired
	    private CategoryMapper mapper;
	    
	    public List<Search> searchCategory(String category) {
	    	return mapper.searchCategory(category);
	    }
}
