package com.medolia.demo.pattern.chain;

import com.medolia.demo.pattern.chain.middleware.Middleware;
import com.medolia.demo.pattern.chain.middleware.RoleCheckMiddleware;
import com.medolia.demo.pattern.chain.middleware.ThrottlingMiddleware;
import com.medolia.demo.pattern.chain.middleware.UserExistsMiddleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lbli@trip.com
 * @since 0.0.1
 */
public class Client {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        // All checks are linked. Client can build various chains using the same
        // components.
        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {
        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }
}
