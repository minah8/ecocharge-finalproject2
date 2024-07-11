package com.example.demo.repository;

import com.example.demo.common.ItemWithSequence;
import com.example.demo.common.Page;
import com.example.demo.common.PageMaker;

import java.util.List;

public interface BoardRepositoryCustom {
    
    List<ItemWithSequence> findAll(Page page, PageMaker pageMaker);
}
