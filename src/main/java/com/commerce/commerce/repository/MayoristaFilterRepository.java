package com.commerce.commerce.repository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import com.commerce.commerce.entity.Mayorista;

@Repository
public class MayoristaFilterRepository{
    EntityManager em;
    List<Predicate> predicates; 

    public MayoristaFilterRepository(EntityManager em) {
        this.em = em;
    }

    public List<Mayorista> findAll(
        String name, 
        String country, 
        String sector, 
        String sortBy
    ){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mayorista> cq = cb.createQuery(Mayorista.class);
        Root<Mayorista> mayorista = cq.from(Mayorista.class);
        this.predicates = getPredicates(cb, mayorista, name, country, sector);

        cq.where(this.predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(mayorista.get(sortBy)));

        return em.createQuery(cq).getResultList();
    }

    private List<Predicate> getPredicates(
            CriteriaBuilder cb,
            Root<Mayorista> mayorista,
            String name, 
            String country, 
            String sector
    ){
        List<Predicate> predicates = new ArrayList<>();

        if(name != null){
            predicates.add(cb.like(mayorista.get("name"), name));
        }

        if(country != null){
            predicates.add(cb.like(mayorista.get("country"), country));
        }

        if(sector != null){
            predicates.add(cb.equal(mayorista.get("sector"), sector));
        }

        return predicates;
    }

    public Page<Mayorista> getFilterPaginate(
        String name, 
        String country, 
        String sector, 
        String sortBy,
        Pageable pageable
    ){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Mayorista> result = this.findAll(name, country, sector, sortBy);

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Mayorista> mayoristaRootCount = countQuery.from(Mayorista.class);
        countQuery.select(cb.count(mayoristaRootCount))
            .where(cb.and(this.predicates.toArray(new Predicate[this.predicates.size()])));

        Long count = em.createQuery(countQuery).getSingleResult();

        Page<Mayorista> resultPaginate = new PageImpl<>(result, pageable, count);
        return resultPaginate;
    }
    public PagedListHolder<Mayorista> getFilterPaginatev2(
            String name,
            String country,
            String sector,
            String sortBy,
            Pageable pageable
    ){
        List<Mayorista> result = this.findAll(name, country, sector, sortBy);
        PagedListHolder<Mayorista> page = new PagedListHolder<>(result);
        page.setPageSize(pageable.getPageSize());
        page.setPage(pageable.getPageNumber());

        return page;
    }

}