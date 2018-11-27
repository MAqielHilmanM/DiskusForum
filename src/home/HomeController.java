/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

/**
 *
 * @author maqielhm
 */
public class HomeController {
    HomeModel model;
    HomeView view;

    public HomeController() {
        model = new HomeModel();
        view = new HomeView();
        view.setVisible(true);
    }
    
    
}
