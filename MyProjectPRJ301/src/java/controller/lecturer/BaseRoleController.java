/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Lecturer;
import model.Role;
import model.Session;

/**
 *
 * @author sonnt
 */
public abstract class BaseRoleController extends BaseAuthenticationController {

    private boolean checkAuthorization(HttpServletRequest req) {
        String currentURL = req.getServletPath();
        System.out.println(currentURL);

        Account account = (Account) req.getSession().getAttribute("acc");
        for (Role role : account.getRoles()) {
            for (Feature feature : role.getFeatures()) {
//                if (feature.getUrl().equals(currentURL)) {
//                    return true;
//                }
                if (currentURL.contains(feature.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected abstract void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuthorization(req)) {
      
            //do bussiness
            processAuthPost(req, resp);
        } else {
            resp.getWriter().println("access denied!1");
        }
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkAuthorization(req)) {
            //do bussiness
            processAuthGet(req, resp);
        } else {
            resp.getWriter().println("access denied!1");
        }
    }

}
