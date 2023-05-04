package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        String localhost = "127.0.0.1";
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        String date = LocalDate.now().format(formatter).toString();
        System.out.println("Введите название");
        String name = scanner.nextLine();
        System.out.println("Введите сумму");
        int sum = Integer.parseInt(scanner.nextLine());
        Deal deal = new Deal(date, name, sum);
        Gson gson = new Gson();
        String message = gson.toJson(deal);

        try(Socket socket = new Socket(localhost, 9889);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ){
            out.println(message + "\n");
            boolean flag = true;
            StringBuilder builder = new StringBuilder();
            while (flag){
                String answer = in.readLine();
                if(answer == null){
                    flag = false;
                }else{
                    builder.append(answer);
                }
            }
            System.out.println(builder.toString());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
