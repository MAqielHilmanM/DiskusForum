/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

/**
 *
 * @author maqielhm
 */
public class RegisterModel {
    static class Request{
        private String name;
        private String email;
        private String username;
        private String password;

        public Request(String name, String email, String username, String password) {
            this.name = name;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    
    }
    static class Response{
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        
    }
}
