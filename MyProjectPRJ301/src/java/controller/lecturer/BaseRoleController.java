/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author sonnt
 */
public abstract class BaseRoleController extends BaseAuthenticationController {

    private boolean checkAuthorization(HttpServletRequest req)
    {
        String currentURL = req.getServletPath();
        Account account = (Account) req.getSession().getAttribute("acc");
        for (Role role : account.getRoles()) {
            for (Feature feature : role.getFeatures()) {
                if(feature.getUrl().equals(currentURL))
                    return true;
            }
        }
        return false;
    }
    
    protected abstract void processAuthPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    protected abstract void processAuthGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    
    
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuthorization(req))
        {
            //do bussiness
            processAuthPost(req, resp);
        }
        else
            resp.getWriter().println("access denied!");
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(checkAuthorization(req))
        {
            //do bussiness
            processAuthGet(req, resp);
        }
        else
            resp.getWriter().println("access denied!");
    }
    
}
