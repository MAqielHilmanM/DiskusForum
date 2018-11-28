/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import auth.AuthController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author maqielhm
 */
public class HomeController {

    private HomeModel mModel;
    private HomeView mView;
    private String mId;

    public HomeController(String id) {
        mModel = new HomeModel();
        mView = new HomeView();
        mView.setVisible(true);
        mId = id;
        initComponents();
    }

    private void initComponents() {
        mView.addPnlMnHomeAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.changeMenu(MenuType.MENU_HOME);
            }
        });
        mView.addPnlMnCommunityAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.changeMenu(MenuType.MENU_COMMUNITY);
            }
        });
        mView.addPnlMnMyThreadAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.changeMenu(MenuType.MENU_MY_THREAD);
            }
        });
        mView.addPnlLogOutAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AuthController();
                mView.dispose();
            }
        });
    }

}
