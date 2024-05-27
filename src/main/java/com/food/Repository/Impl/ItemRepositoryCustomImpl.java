package com.food.Repository.Impl;

import com.food.Entities.Item;
import com.food.Repository.ItemRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private final EntityManager entityManager;
    @Override
    public List<Item> searchItem(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> cq=cb.createQuery(Item.class);
        Root<Item> stud=cq.from(Item.class);
        ArrayList<Predicate> predicates = new ArrayList<>();
        if(name!=null) {
            Predicate itemName = cb.like(stud.get("itemName"), "%" + name + "%");
            predicates.add(itemName);
        }
        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Item> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
