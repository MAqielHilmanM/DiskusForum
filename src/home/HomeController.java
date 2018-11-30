/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import auth.AuthController;
import home.comment.CommentModel;
import home.community.CommunityModel;
import home.community_thread.CommunityThreadModel;
import home.hot_thread.HotThreadModel;
import home.member.MemberModel;
import home.my_thread.MyThreadModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;

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
        mView.addPnlProfileAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mView.changeMenu(MenuType.MENU_PROFILE);
            }
        });
        
        loadListCommunityL();
        loadListCommunityRMember();
        loadListCommunityRThread();
        loadListHomeL();
        loadListHomeR();
        loadListMyThreadL();
        loadListMyThreadR();
    }

    public void loadListHomeL(){
        DefaultListModel<HotThreadModel> dlm = new DefaultListModel<>();
        dlm.addElement(new HotThreadModel("", "", "John Doe", "Loream Comunity", "Lorem Ipsum", "...", "10 Jan 2018"));
        dlm.addElement(new HotThreadModel("", "", "John Doe", "Loream Comunity", "Lorem Ipsum", "...", "10 Jan 2018"));
        dlm.addElement(new HotThreadModel("", "", "John Doe", "Loream Comunity", "Lorem Ipsum", "...", "10 Jan 2018"));
        mView.setLstSubHomeL(dlm);
    }
    
    public void loadListMyThreadL(){
        DefaultListModel<MyThreadModel> dlm = new DefaultListModel<>();
        dlm.addElement(new MyThreadModel("", "10 Jan 2018", "Lorem Ipsum", "Lorem Ipsum", ".."));
        dlm.addElement(new MyThreadModel("", "10 Jan 2018", "Lorem Ipsum", "Lorem Ipsum", ".."));
        dlm.addElement(new MyThreadModel("", "10 Jan 2018", "Lorem Ipsum", "Lorem Ipsum", ".."));
        mView.setLstSubMyThreadL(dlm);
    }
    
    public void loadListCommunityL(){
        DefaultListModel<CommunityModel> dlm = new DefaultListModel<>();
        dlm.addElement(new CommunityModel("", "Lorem Ipsum", "Lorem Ipsum", "..", "10 Jan 2018"));
        mView.setLstSubCommunityL(dlm);
    }
    
    public void loadListHomeR(){
        DefaultListModel<CommentModel> dlm = new DefaultListModel<>();
        dlm.addElement(new CommentModel("", "", "10 Jan 2018", "...","John Doe"));
        mView.setLstSubHomeR(dlm);
    }
    
    public void loadListMyThreadR(){
        DefaultListModel<CommentModel> dlm = new DefaultListModel<>();
        dlm.addElement(new CommentModel("", "", "10 Jan 2018", "...","John Doe"));
        mView.setLstSubMyThreadR(dlm);
    }
    
    public void loadListCommunityRThread(){
        DefaultListModel<CommunityThreadModel> dlm = new DefaultListModel<>();
        dlm.addElement(new CommunityThreadModel("", "10 Jan 18", "Lorem Ipsum", "..."));
        dlm.addElement(new CommunityThreadModel("", "10 Jan 18", "Lorem Ipsum", "..."));
        mView.setLstSubCommunityRThread(dlm);
    }
    public void loadListCommunityRMember(){
        DefaultListModel<MemberModel> dlm = new DefaultListModel<>();
        dlm.addElement(new MemberModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        dlm.addElement(new MemberModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        dlm.addElement(new MemberModel("", "John Doe", "Veteran at", "Lorem Ipsum", "Lorem Community", "12", "Online"));
        mView.setLstSubCommunityRMember(dlm);
    }
}
