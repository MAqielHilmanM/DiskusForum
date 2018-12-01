/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.util.List;

/**
 *
 * @author maqielhm
 * @param <T> ResponseClass
 * @param <K> RequestClass
 */
public interface BaseModel<T> {
    public boolean  insert(Object request);
    public boolean  delete(String id);
    public boolean  update(Object request);
    public List<T>  findAll();
    public List<T>  findAllBy(Object request);
    public T        findSingleBy(Object request);
}