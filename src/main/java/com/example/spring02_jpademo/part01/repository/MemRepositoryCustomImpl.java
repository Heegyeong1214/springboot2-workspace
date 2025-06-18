package com.example.spring02_jpademo.part01.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.spring02_jpademo.part01.entity.MemEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

//2. MemRepositoryCustom 구현 클래스
@Repository
public class MemRepositoryCustomImpl implements MemRepositoryCustom{
	@PersistenceContext
    private EntityManager em;
	
	public MemRepositoryCustomImpl() {
		// TODO Auto-generated constructor stub
	}

    @Override
    public List<MemEntity> findByDynamicName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MemEntity> cq = cb.createQuery(MemEntity.class);
        Root<MemEntity> memRoot = cq.from(MemEntity.class);

        Predicate namePredicate = cb.equal(memRoot.get("name"), name);
        cq.where(namePredicate);

        return em.createQuery(cq).getResultList();
    }
	}

