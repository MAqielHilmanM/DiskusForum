/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.util.List;

/**
 *
 * @author maqielhm
 */
public class HomeModel {
    String name;
    String urlPhoto;
    List<ListThreadModel> lists;

    private static class ListThreadModel {

        public ListThreadModel() {
        }
    }
    
    private class ThreadModel{
    
    }
}
