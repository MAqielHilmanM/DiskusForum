/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

import base.BaseController;
import home.HomeController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.Helper;

/**
 *
 * @author maqielhm
 */
public class AuthController implements BaseController {

    private AuthView view;
    private AuthModel model;
    private AuthModel.Request request;
    private AuthModel.Response response;

    public AuthController() {
        view = new AuthView();
        model = new AuthModel();
        view.setVisible(true);
        
        initComponent();
    }

    public void onClickLogin() {
        if (isLoginValid()) {
            request = model.new Request(view.getLoginUsername(), view.getLoginPassword());
            response = model.findBy(request);
            if (response == null) {
                System.out.println("DATA NOT FOUND");
                view.showMessage("Oops. . . Please Check Your Ceredentials.", "Login Failed", 0);
            } else {
                System.out.println("DATA FOUND");
                new HomeController(response.getId());
                view.dispose();
            }
        } else {
            view.showMessage("Please insert right value.", "Login Failed", 0);
        }
    }

    public void onClickRegister() {
        if (isRegisterValid()) {
            request = model.new Request(view.getRegisterName(), view.getRegisterEmail(), view.getRegisterUsername(), view.getRegisterPassword());
            boolean isSuccess = model.insert(request);
            if (isSuccess) {
                System.out.println("Register Succesful");
                view.showMessage("Thank You for Register, Please login to continue.", "Register Complete", 1);
                view.showLoginPage();
            } else {
                System.out.println("Register Failed");
                view.showMessage("Oops . . . Failed To Register", "Register Failed", 0);
            }
        } else {
            view.showMessage("Please insert right value.", "Register Failed", 0);
        }
    }

    @Override
    public void initComponent() {
        view.addLblLoginButtonAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickLogin();
            }
        });

        view.addLblLoginRegisterAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.showRegisterPage();
            }
        });

        view.addLblRegisterButtonAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClickRegister();
            }
        });

        view.addLblRegisterLoginAdapter(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.showLoginPage();
            }
        });
    }

    public Boolean isRegisterValid() {
        boolean isValid = true;
        if (view.getRegisterEmail().equals("") || view.getRegisterEmail().equals("Email") || !Helper.checkEmailRegex(view.getRegisterEmail())) {
            return false;
        }
        if (view.getRegisterName().equals("") || view.getRegisterName().equals("Name")) {
            return false;
        }
        if (view.getRegisterUsername().equals("") || view.getRegisterUsername().equals("Username")) {
            return false;
        }
        if (view.getRegisterPassword().equals("") || view.getRegisterPassword().equals("Password")) {
            return false;
        }
        return isValid;
    }

    public Boolean isLoginValid() {
        boolean isValid = true;
        if (view.getLoginUsername().equals("") || view.getLoginUsername().equals("Username")) {
            return false;
        }
        if (view.getLoginPassword().equals("") || view.getLoginPassword().equals("Password")) {
            return false;
        }
        return isValid;
    }
}
