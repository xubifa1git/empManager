package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Dep;

@Service("depDao")
public interface IDepMapper {
public List<Dep> findAll();
}
