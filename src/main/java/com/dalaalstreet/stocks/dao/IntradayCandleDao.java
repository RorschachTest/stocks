package com.dalaalstreet.stocks.dao;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface IntradayCandleDao extends CrudRepository<IntradayCandleDao, Long> {



}
