/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammi140.Impl;

import Grammi140.Models.User.Authoritie;
import Grammi140.Models.User.User;
import java.util.List;

/**
 *
 * @author Chahir Chalouati
 */
public interface UserImpl {

    boolean SignIn(User user, List< Authoritie> authoritie);

}
