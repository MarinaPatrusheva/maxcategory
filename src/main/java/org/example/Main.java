package org.example;

import com.google.gson.Gson;
import org.example.category.MaxCategory;
import org.example.category.MaxCategoryByDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String answerClient;
        TransactionHistory history = new TransactionHistory();
        MaxCategory category = new MaxCategory();
        try(ServerSocket serverSocket = new ServerSocket(9889)){
            while (true){
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ){
                    answerClient = in.readLine();
                    System.out.println(answerClient);
                    history.saveJson(readJson(answerClient));
                    out.print(category.getJsonAllMaxCategory(history.getDealList()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Deal readJson(String json){
        Gson gson = new Gson();
        Deal deal = gson.fromJson(json, Deal.class);
        return deal;
    }
}