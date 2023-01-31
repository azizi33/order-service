package com.melita.erp.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.melita.erp.orderservice.exception.ErrorDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Configuration
@Slf4j
public class AuthManagerFilter extends OncePerRequestFilter {


    @Autowired
    UserDetailsService userDetailsService;
    UserDetails userDetails;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String userEmail = request.getHeader("authorization") == null ? null : request.getHeader("authorization");
        if (userEmail == null) {
            log.error("Header must not be null ");
            // response.sendError(401,"unknown server request with Ip Address");
            response.setContentType("application/json");
            response.setStatus(401);
            PrintWriter out=response.getWriter();
            ErrorDetails errorDetails=new ErrorDetails(new Date(),400,"BAD_REQUEST","user header is missing authorization","user header is missing or empty authorization" );
            out.write(new ObjectMapper().writeValueAsString(errorDetails));
            out.flush();
            return;
        }
        try {
            userDetails = userDetailsService.loadUserByUsername(userEmail);
            if(userDetails.getUsername()==null || userDetails.getUsername().isEmpty()){

                log.error("User {} not found  with role {}"+ userEmail +userDetails.getAuthorities().toArray());
                // response.sendError(401,"unknown server request with Ip Address");
                response.setContentType("application/json");
                response.setStatus(400);
                PrintWriter out=response.getWriter();
                ErrorDetails errorDetails=new ErrorDetails(new Date(),400,"NOT_FOUND","user not found "+userEmail,"unable to authenticate User "+userEmail);
                out.write(new ObjectMapper().writeValueAsString(errorDetails));
                out.flush();
                return;
            }
            log.info(userDetails.getUsername()+" ");
            userDetails.getAuthorities().stream().forEach(role->{
                log.info(""+role);
            });

        }catch (Exception ex){
            response.setContentType("application/json");
            response.setStatus(400);
            PrintWriter out=response.getWriter();
            ErrorDetails errorDetails=new ErrorDetails(new Date(),400,"NOT_FOUND","user not found "+userEmail,"unable to authenticate User "+userEmail);
            out.write(new ObjectMapper().writeValueAsString(errorDetails));
            out.flush();
            return;
        }

    }
}
