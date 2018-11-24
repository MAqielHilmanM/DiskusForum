/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.query;

/**
 *
 * @author maqielhm
 */
public interface ApiOperatorInterface<K>{
    public K conditionEqual(Object a,Object b);
    public K conditionNotEqual(Object a,Object b);
    public K conditionGraterThanOrEqual(Object a,Object b);
    public K conditionLessThanOrEqual(Object a,Object b);
    public K conditionLessThan(Object a, Object b);
    public K conditionGraterThan(Object a, Object b);
    public K conditionBetween(String column,Object a, Object b);
    public K conditionLike(String column, String b);
    public K conditionIn(String column, Object[] b);
}

