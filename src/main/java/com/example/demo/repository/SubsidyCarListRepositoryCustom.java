package com.example.demo.repository;

import com.example.demo.common.Page;
import com.example.demo.common.PageMaker;
import com.example.demo.dto.response.SubsidyCarResponseDTO;
import com.example.demo.entity.SubsidyCar;

import java.util.List;

public interface SubsidyCarListRepositoryCustom {
    
    List<SubsidyCar> findAll(Page page);
    
}