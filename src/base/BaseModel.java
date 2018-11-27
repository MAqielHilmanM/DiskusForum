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
 */
public interface BaseModel<T,K> {
    public boolean  insert(K request);
    public boolean  delete(String id);
    public boolean  update(K request);
    public List<T>  findAll();
    public T        findById(String id);
    public T        findBy(K request);
}